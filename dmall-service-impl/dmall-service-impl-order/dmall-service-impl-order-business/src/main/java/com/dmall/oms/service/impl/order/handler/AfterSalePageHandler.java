package com.dmall.oms.service.impl.order.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.aftersalepage.AfterSalePageRequestDTO;
import com.dmall.oms.api.dto.aftersalepage.AfterSalePageResponseDTO;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.AfterSaleTypeEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 售后分页处理器
 * @author: created by hang.yu on 2020/4/14 23:21
 */
@Component
public class AfterSalePageHandler extends AbstractCommonHandler<AfterSalePageRequestDTO, OrderAfterSaleApplyDO, AfterSalePageResponseDTO> {

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Override
    public BaseResult<ResponsePage<AfterSalePageResponseDTO>> processor(AfterSalePageRequestDTO requestDTO) {
        IPage<OrderAfterSaleApplyDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        LambdaQueryWrapper<OrderAfterSaleApplyDO> wrapper = Wrappers.<OrderAfterSaleApplyDO>lambdaQuery()
                .eq(requestDTO.getAfterSaleId() != null, OrderAfterSaleApplyDO::getId, requestDTO.getAfterSaleId())
                .eq(requestDTO.getOrderId() != null, OrderAfterSaleApplyDO::getOrderId, requestDTO.getOrderId())
                .eq(requestDTO.getOrderItemId() != null, OrderAfterSaleApplyDO::getOrderItemId, requestDTO.getOrderItemId())
                .eq(requestDTO.getSubOrderId() != null, OrderAfterSaleApplyDO::getSubOrderId, requestDTO.getSubOrderId())
                .eq(requestDTO.getType() != null, OrderAfterSaleApplyDO::getType, requestDTO.getType())
                .eq(requestDTO.getStatus() != null, OrderAfterSaleApplyDO::getStatus, requestDTO.getStatus())
                .eq(requestDTO.getSkuId() != null, OrderAfterSaleApplyDO::getSkuId, requestDTO.getSkuId());
        page = orderAfterSaleApplyMapper.selectPage(page, wrapper);

        return ResultUtil.success(new ResponsePage<>(page.getTotal(), buildResponse(page)));
    }

    private List<AfterSalePageResponseDTO> buildResponse(IPage<OrderAfterSaleApplyDO> page) {
        return page.getRecords().stream().map(orderAfterSaleApplyDO -> {
            AfterSalePageResponseDTO afterSalePageResponseDTO = new AfterSalePageResponseDTO();
            afterSalePageResponseDTO.setAfterSaleId(orderAfterSaleApplyDO.getId());
            afterSalePageResponseDTO.setOrderId(orderAfterSaleApplyDO.getOrderId());
            afterSalePageResponseDTO.setOrderItemId(orderAfterSaleApplyDO.getOrderItemId());
            afterSalePageResponseDTO.setType(EnumUtil.getCodeDescEnum(AfterSaleTypeEnum.class, orderAfterSaleApplyDO.getType()));
            afterSalePageResponseDTO.setStatus(EnumUtil.getCodeDescEnum(AfterSaleStatusEnum.class, orderAfterSaleApplyDO.getStatus()));
            afterSalePageResponseDTO.setHandleMan(orderAfterSaleApplyDO.getHandleMan());
            afterSalePageResponseDTO.setApplyTime(orderAfterSaleApplyDO.getApplyTime());
            afterSalePageResponseDTO.setHandleTime(orderAfterSaleApplyDO.getHandleTime());
            afterSalePageResponseDTO.setRefundTime(orderAfterSaleApplyDO.getRefundTime());
            afterSalePageResponseDTO.setFillLogisticsNoTime(orderAfterSaleApplyDO.getFillLogisticsNoTime());
            afterSalePageResponseDTO.setReceiveTime(orderAfterSaleApplyDO.getReceiveTime());
            afterSalePageResponseDTO.setRefuseTime(orderAfterSaleApplyDO.getRefuseTime());
            afterSalePageResponseDTO.setCloseTime(orderAfterSaleApplyDO.getCloseTime());
            afterSalePageResponseDTO.setLogisticsNo(orderAfterSaleApplyDO.getLogisticsNo());
            afterSalePageResponseDTO.setSkuId(orderAfterSaleApplyDO.getSkuId());
            return afterSalePageResponseDTO;

        }).collect(Collectors.toList());
    }
}
