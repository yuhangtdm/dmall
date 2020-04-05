package com.dmall.oms.service.impl.order.handler;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageResponseDTO;
import com.dmall.oms.api.enums.CancelTypeEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 拆单分页
 * @author: created by hang.yu on 2020/4/4 23:19
 */
@Component
public class DemolitionOrderPageHandler extends AbstractCommonHandler<DemolitionOrderPageRequestDTO, OrderDO, DemolitionOrderPageResponseDTO> {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseResult<ResponsePage<DemolitionOrderPageResponseDTO>> processor(DemolitionOrderPageRequestDTO requestDTO) {
        DateTime startDayTime = DateUtil.beginOfDay(requestDTO.getCreateTime());
        DateTime endDayTime = DateUtil.endOfDay(requestDTO.getCreateTime());

        LambdaQueryWrapper<OrderDO> queryWrapper = Wrappers.<OrderDO>lambdaQuery()
                .like(requestDTO.getOrderId() != null, OrderDO::getId, requestDTO.getOrderId())
                .eq(requestDTO.getSource() != null, OrderDO::getSource, requestDTO.getSource())
                .eq(requestDTO.getMemberId() != null, OrderDO::getCreator, requestDTO.getMemberId())
                .eq(requestDTO.getIsSplit() != null, OrderDO::getSplit, requestDTO.getIsSplit())
                .eq(requestDTO.getCancelType() != null, OrderDO::getCancelType, requestDTO.getCancelType())
                .eq(requestDTO.getOrderStatus() != null, OrderDO::getStatus, requestDTO.getOrderStatus())
                .between(OrderDO::getGmtCreated, startDayTime, endDayTime);

        IPage<OrderDO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        page = orderMapper.selectPage(page, queryWrapper);
        List<DemolitionOrderPageResponseDTO> list = page.getRecords().stream().map(orderDO -> {
            DemolitionOrderPageResponseDTO responseDTO = new DemolitionOrderPageResponseDTO();
            responseDTO.setOrderId(orderDO.getId());
            responseDTO.setOrderStatus(EnumUtil.getKeyValueEnum(OrderStatusEnum.class, orderDO.getStatus()));
            responseDTO.setSource(EnumUtil.getKeyValueEnum(SourceEnum.class, orderDO.getSource()));
            responseDTO.setMemberId(orderDO.getCreator());
            responseDTO.setProductCount(orderDO.getProductCount());
            responseDTO.setSkuCount(orderDO.getSkuCount());
            responseDTO.setOrderAmount(orderDO.getOrderAmount());
            responseDTO.setPayAmount(orderDO.getPayAmount());
            responseDTO.setDealAmount(orderDO.getDealAmount());
            responseDTO.setTotalSkuAmount(orderDO.getTotalSkuAmount());
            responseDTO.setFreightAmount(orderDO.getFreightAmount());
            responseDTO.setIsSplit(EnumUtil.getKeyValueEnum(SplitEnum.class, orderDO.getSplit()));
            responseDTO.setReceiverName(orderDO.getReceiverName());
            responseDTO.setReceiverPhone(orderDO.getReceiverPhone());
            responseDTO.setLogisticsNo(orderDO.getLogisticsNo());
            responseDTO.setPaymentTime(orderDO.getPaymentTime());
            responseDTO.setReceiveTime(orderDO.getReceiveTime());
            responseDTO.setCancelTime(orderDO.getReceiveTime());
            responseDTO.setDeleteTime(orderDO.getDeleteTime());
            responseDTO.setInvoiceTime(orderDO.getInvoiceTime());
            responseDTO.setCancelType(EnumUtil.getKeyValueEnum(CancelTypeEnum.class, orderDO.getCancelType()));
            responseDTO.setCreateTime(orderDO.getGmtCreated());
            return responseDTO;
        }).collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage<DemolitionOrderPageResponseDTO>(page.getTotal(), list));
    }
}
