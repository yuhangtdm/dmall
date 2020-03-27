package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.threadpool.ThreadPoolUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberreceiveaddress.response.ListReceiveAddressResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.request.TradeSkuRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import com.dmall.oms.feign.MemberAddressFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
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

    private SkuFeign skuFeign;

    private MemberAddressFeign memberAddressFeign;



    @Override
    public BaseResult<ToTradeResponseDTO> processor(ToTradeRequestDTO requestDTO) {

        List<Long> skuIds = requestDTO.getTradeSku().stream().map(TradeSkuRequestDTO::getSkuId).collect(Collectors.toList());

        //  异步的调用接口
        // 查看调用sku信息
        Future<BaseResult<List<BasicSkuResponseDTO>>> skuFuture = pool.submit(() -> skuFeign.getBasic(skuIds));

        // 调用会员地址接口
        Future<BaseResult<List<ListReceiveAddressResponseDTO>>> addressFuture = pool.submit(() -> memberAddressFeign.list());

        // 调用会员发票接口


        return null;
    }
}
