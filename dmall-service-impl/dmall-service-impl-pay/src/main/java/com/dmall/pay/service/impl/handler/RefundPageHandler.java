package com.dmall.pay.service.impl.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pay.api.dto.refundpage.RefundPageRequestDTO;
import com.dmall.pay.api.dto.refundpage.RefundPageResponseDTO;
import com.dmall.pay.generator.dataobject.RefundRecordDO;
import com.dmall.pay.generator.mapper.RefundRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 退款明细分页处理器
 * @author: created by hang.yu on 2020/4/16 23:18
 */
@Component
public class RefundPageHandler extends AbstractCommonHandler<RefundPageRequestDTO, RefundRecordDO, RefundPageResponseDTO> {

    @Autowired
    private RefundRecordMapper refundRecordMapper;

    @Override
    public BaseResult processor(RefundPageRequestDTO requestDTO) {
        IPage<RefundRecordDO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();

        LambdaQueryWrapper<RefundRecordDO> wrapper = Wrappers.<RefundRecordDO>lambdaQuery()
                .eq(requestDTO.getAfterSaleId() != null, RefundRecordDO::getAfterSaleId, requestDTO.getAfterSaleId())
                .eq(RefundRecordDO::getCreator, portalMemberDTO.getId());
        page = refundRecordMapper.selectPage(page, wrapper);
        List<RefundPageResponseDTO> collect = page.getRecords().stream().map(refundRecord -> {
            RefundPageResponseDTO refundPageResponse = new RefundPageResponseDTO();
            refundPageResponse.setAfterSaleId(refundRecord.getAfterSaleId());
            refundPageResponse.setOrderId(refundRecord.getOrderId());
            refundPageResponse.setSkuId(refundRecord.getSkuId());
            refundPageResponse.setSkuName(refundRecord.getSkuName());
            refundPageResponse.setSkuNumber(refundRecord.getSkuNumber());
            refundPageResponse.setSkuToTalPrice(refundRecord.getAmount());
            return refundPageResponse;
        }).collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage(page.getTotal(), collect));
    }
}
