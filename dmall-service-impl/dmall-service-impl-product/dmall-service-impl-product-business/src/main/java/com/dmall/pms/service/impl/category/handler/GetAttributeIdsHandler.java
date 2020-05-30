package com.dmall.pms.service.impl.category.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.category.response.CanScreenResponseDTO;
import com.dmall.pms.api.enums.CanScreenEnum;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.service.support.CategoryAttributeSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: GetAttributeIdsHandler
 * @author: created by hang.yu on 2020/5/27 22:20
 */
@Component
public class GetAttributeIdsHandler extends AbstractCommonHandler<Long, CategoryAttributeDO, List<CanScreenResponseDTO>> {

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<List<CanScreenResponseDTO>> processor(Long categoryId) {
        pmsValidate.validateThreeLevelCategory(categoryId);
        List<CanScreenResponseDTO> result = categoryAttributeSupport.listByCategoryId(categoryId)
                .stream().map(categoryAttributeDO -> {
                    CanScreenResponseDTO canScreen = new CanScreenResponseDTO();
                    canScreen.setId(String.valueOf(categoryAttributeDO.getAttributeId()));
                    canScreen.setName(categoryAttributeDO.getCanScreen());
                    canScreen.setValue(EnumUtil.getDesc(CanScreenEnum.class, categoryAttributeDO.getCanScreen()));
                    return canScreen;
                })
                .collect(Collectors.toList());
        return ResultUtil.success(result);
    }
}
