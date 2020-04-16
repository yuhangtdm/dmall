package com.dmall.oms.service.impl.order.handler;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.myaftersalepage.MyAfterSalePageRequestDTO;
import com.dmall.oms.api.dto.myaftersalepage.MyAfterSalePageResponseDTO;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.service.impl.order.mapper.MyAfterSalePageMapper;
import com.dmall.oms.service.impl.order.mapper.dto.MyAfterSalePageDbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 我的售后单分页
 * @author: created by hang.yu on 2020/4/16 22:50
 */
@Component
public class MyAfterSalePageHandler extends AbstractCommonHandler<MyAfterSalePageRequestDTO, OrderAfterSaleApplyDO, MyAfterSalePageResponseDTO> {

    @Autowired
    private MyAfterSalePageMapper afterSalePageMapper;

    @Override
    public BaseResult<ResponsePage<MyAfterSalePageResponseDTO>> processor(MyAfterSalePageRequestDTO requestDTO) {
        Page page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        List<MyAfterSalePageResponseDTO> myAfterSalePageResponseDTOS =
                afterSalePageMapper.myAfterSalePage(page, buildRequest(requestDTO));
        return ResultUtil.success(new ResponsePage<>(page.getTotal(), myAfterSalePageResponseDTOS));
    }

    private MyAfterSalePageDbDTO buildRequest(MyAfterSalePageRequestDTO request) {
        MyAfterSalePageDbDTO myAfterSalePage = new MyAfterSalePageDbDTO();
        myAfterSalePage.setAfterSaleId(request.getAfterSaleId());
        myAfterSalePage.setOrderId(request.getOrderId());
        myAfterSalePage.setStatus(request.getStatus());
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        myAfterSalePage.setCreator(portalMemberDTO.getId());
        return myAfterSalePage;
    }
}
