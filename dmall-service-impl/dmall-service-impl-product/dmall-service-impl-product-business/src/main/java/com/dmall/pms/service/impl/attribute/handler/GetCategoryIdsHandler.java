package com.dmall.pms.service.impl.attribute.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.service.support.CategoryAttributeSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: GetCategoryIdsHandler
 * @author: created by hang.yu on 2020/5/27 22:20
 */
@Component
@Deprecated
public class GetCategoryIdsHandler extends AbstractCommonHandler<Long, CategoryAttributeDO, List<String>> {

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<List<String>> processor(Long attributeId) {
        pmsValidate.validateAttribute(attributeId);
        List<String> result = categoryAttributeSupport.listByAttributeId(attributeId)
            .stream().map(CategoryAttributeDO::getCategoryId)
            .map(String::valueOf).collect(Collectors.toList());
        return ResultUtil.success(result);
    }
}
