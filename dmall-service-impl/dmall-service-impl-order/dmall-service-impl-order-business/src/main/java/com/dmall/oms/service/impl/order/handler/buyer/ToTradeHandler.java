package com.dmall.oms.service.impl.order.handler.buyer;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.FreightPriceUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.common.util.threadpool.ThreadPoolUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberinvoice.GetMemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.response.ListReceiveAddressResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.request.TradeSkuRequestDTO;
import com.dmall.oms.api.dto.totrade.response.AddressResponseDTO;
import com.dmall.oms.api.dto.totrade.response.InvoiceResponseDTO;
import com.dmall.oms.api.dto.totrade.response.SkuResponseDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import com.dmall.oms.feign.MemberAddressFeign;
import com.dmall.oms.feign.MemberInvoiceFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.service.impl.order.OrderConstants;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @description: ToTradeHandler
 * @author: created by hang.yu on 2020/3/26 23:03
 */
@Component
public class ToTradeHandler extends AbstractCommonHandler<ToTradeRequestDTO, Void, ToTradeResponseDTO> {

    private ThreadPoolExecutor pool = ThreadPoolUtil.createThreadPool("trade");

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private MemberAddressFeign memberAddressFeign;

    @Autowired
    private MemberInvoiceFeign memberInvoiceFeign;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public BaseResult<ToTradeResponseDTO> processor(ToTradeRequestDTO requestDTO) {
        // 获取登录的会员信息
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        // 异步调用sku信息
        Future<List<SkuResponseDTO>> skuFuture = pool.submit(() -> getSkuList(requestDTO));
        // 异步会员地址接口
        Future<List<AddressResponseDTO>> addressFuture = pool.submit(this::getMemberAddressFeign);
        // 异步会员发票接口
        Future<InvoiceResponseDTO> invoiceFuture = pool.submit(() -> getMemberInvoice(loginMember.getId()));
        return getTradeResponse(skuFuture, addressFuture, invoiceFuture, requestDTO.getTradeSku(), loginMember.getId());
    }


    /**
     * 异步获取sku数据
     */
    private List<SkuResponseDTO> getSkuList(ToTradeRequestDTO requestDTO) {
        List<Long> skuIds = requestDTO.getTradeSku().stream().map(TradeSkuRequestDTO::getSkuId).collect(Collectors.toList());
        Map<Long, Integer> skuCountMap = requestDTO.getTradeSku().stream()
                .collect(Collectors.toMap(TradeSkuRequestDTO::getSkuId, TradeSkuRequestDTO::getCount));
        BaseResult<List<BasicSkuResponseDTO>> listBaseResult = skuFeign.getBasic(skuIds);
        if (!listBaseResult.getResult()) {
            throw new BusinessException(BasicStatusEnum.FAIL);
        }
        return listBaseResult.getData().stream()
                .map(basicSku -> {
                    SkuResponseDTO skuResponseDTO = new SkuResponseDTO();
                    skuResponseDTO.setSkuId(basicSku.getId());
                    skuResponseDTO.setSkuName(basicSku.getName());
                    skuResponseDTO.setSkuSpecificationsJson(basicSku.getSkuSpecificationsJson());
                    skuResponseDTO.setSkuNumber(skuCountMap.get(basicSku.getId()));
                    skuResponseDTO.setSkuPrice(basicSku.getPrice());
                    skuResponseDTO.setSkuTotalPrice(NumberUtil.mul(basicSku.getPrice(), skuResponseDTO.getSkuNumber()));
                    skuResponseDTO.setHasStock(basicSku.getSalableStock() > 0);
                    skuResponseDTO.setWeight(basicSku.getWeight());
                    return skuResponseDTO;
                }).collect(Collectors.toList());
    }

    /**
     * 异步获取收货地址数据
     */
    private List<AddressResponseDTO> getMemberAddressFeign() {
        BaseResult<List<ListReceiveAddressResponseDTO>> listBaseResult = memberAddressFeign.list();
        if (!listBaseResult.getResult()) {
            throw new BusinessException(BasicStatusEnum.FAIL);
        }
        return listBaseResult.getData().stream()
                .map(addressResponseDTO -> {
                    AddressResponseDTO addressResponse = new AddressResponseDTO();
                    addressResponse.setId(addressResponseDTO.getId());
                    addressResponse.setName(addressResponseDTO.getName());
                    addressResponse.setPhone(addressResponseDTO.getPhone());
                    addressResponse.setDefaultStatus(addressResponseDTO.getDefaultStatus());
                    addressResponse.setProvince(addressResponseDTO.getProvince());
                    addressResponse.setCity(addressResponseDTO.getCity());
                    addressResponse.setRegion(addressResponseDTO.getRegion());
                    addressResponse.setDetailAddress(addressResponseDTO.getDetailAddress());
                    return addressResponse;
                })
                .collect(Collectors.toList());
    }

    /**
     * 异步获取会员发票数据
     */
    private InvoiceResponseDTO getMemberInvoice(Long memberId) {

        BaseResult<GetMemberInvoiceResponseDTO> memberResponse = memberInvoiceFeign.getByMemberId(memberId);
        if (!memberResponse.getResult()) {
            throw new BusinessException(BasicStatusEnum.FAIL);
        }
        GetMemberInvoiceResponseDTO memberInvoiceResponseDTO = memberResponse.getData();
        InvoiceResponseDTO invoiceResponse = new InvoiceResponseDTO();
        invoiceResponse.setId(memberInvoiceResponseDTO.getId());
        invoiceResponse.setInvoiceHeader(memberInvoiceResponseDTO.getInvoiceHeader());
        invoiceResponse.setPersonalName(memberInvoiceResponseDTO.getPersonalName());
        invoiceResponse.setReceiverPhone(memberInvoiceResponseDTO.getReceiverPhone());
        invoiceResponse.setReceiverEmail(memberInvoiceResponseDTO.getReceiverEmail());
        invoiceResponse.setCompanyName(memberInvoiceResponseDTO.getCompanyName());
        invoiceResponse.setCustomerTaxNumber(memberInvoiceResponseDTO.getCustomerTaxNumber());
        invoiceResponse.setInvoiceContent(memberInvoiceResponseDTO.getInvoiceContent());
        return invoiceResponse;
    }

    /**
     * 构建返回数据
     */
    private BaseResult<ToTradeResponseDTO> getTradeResponse(Future<List<SkuResponseDTO>> skuFuture,
                                                            Future<List<AddressResponseDTO>> addressFuture,
                                                            Future<InvoiceResponseDTO> invoiceFuture,
                                                            List<TradeSkuRequestDTO> tradeSku,
                                                            Long memberId) {
        ToTradeResponseDTO tradeResponseDTO = new ToTradeResponseDTO();
        try {
            tradeResponseDTO.setSkuList(skuFuture.get());
            tradeResponseDTO.setAddressList(addressFuture.get());
            tradeResponseDTO.setInvoiceResponseDTO(invoiceFuture.get());
            // sku总数量
            int totalSkuNumber = tradeSku.stream().mapToInt(TradeSkuRequestDTO::getCount).sum();
            tradeResponseDTO.setSkuTotalNumber(totalSkuNumber);
            // sku总价格
            BigDecimal totalSkuPrice = tradeResponseDTO.getSkuList().stream().map(SkuResponseDTO::getSkuTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            tradeResponseDTO.setSkuTotalPrice(totalSkuPrice);
            tradeResponseDTO.setFreightPrice(FreightPriceUtil.getFreightPrice(totalSkuPrice));
            tradeResponseDTO.setTotalPrice(NumberUtil.add(tradeResponseDTO.getSkuTotalPrice(),
                    tradeResponseDTO.getFreightPrice()));
        } catch (Exception e) {
            return ResultUtil.fail(BasicStatusEnum.FAIL);
        }
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(StrUtil.format(OrderConstants.ORDER_KEY, memberId), token);
        tradeResponseDTO.setOrderKey(token);
        return ResultUtil.success(tradeResponseDTO);
    }
}
