package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attributetype.request.BatchSaveAttributeTypeRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.AttributeTypeSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @description: 新增属性类别处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class BatchSaveAttributeTypeHandler
    extends AbstractCommonHandler<BatchSaveAttributeTypeRequestDTO, AttributeTypeDO, Void> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private AttributeTypeSupport attributeTypeSupport;

    @Override
    public BaseResult<Void> processor(BatchSaveAttributeTypeRequestDTO requestDTO) {
        pmsValidate.validateCategory(requestDTO.getCategoryId());
        Optional<String> any = requestDTO.getShowNames().stream().filter(StrUtil::isBlank).findAny();
        if (any.isPresent()) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_SHOW_NAME_EMPTY);
        }
        List<AttributeTypeDO> list = attributeTypeSupport.listByCategoryId(requestDTO.getCategoryId());
        int sort = 0;
        if (CollUtil.isNotEmpty(list)) {
            sort = list.size();
        }
        for (int i = 0; i < requestDTO.getShowNames().size(); i++) {
            String showName = requestDTO.getShowNames().get(i);
            AttributeTypeDO attributeType = new AttributeTypeDO();
            attributeType.setCategoryId(requestDTO.getCategoryId());
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            attributeType.setCascadeCategoryId(categoryDO.getPath());
            attributeType.setName(StrUtil.format("{}_{}", categoryDO.getName(), showName));
            attributeType.setShowName(showName);
            attributeType.setSort(sort + i + 1);
            attributeTypeCacheService.insert(attributeType);
        }
        return ResultUtil.success();
    }

}
