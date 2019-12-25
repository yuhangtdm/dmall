package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class DeleteAttributeTypeHandler extends AbstractCommonHandler<Long, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeMapper attributeMapper;


    @Override
    public BaseResult<Long> validate(Long id) {
        AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(id);
        if (attributeTypeDO == null) {
            return ResultUtil.fail(AttributeTypeErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
        }
//        List<AttributeDO> attributeDOS = attributeMapper.selectList(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getAttributeTypeId, id));
       /* if (CollUtil.isNotEmpty(attributeDOS)) {
            return ResultUtil.fail(AttributeTypeErrorEnum.ATTRIBUTE_TYPE_HAS_ATTRIBUTE);
        }*/
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        attributeTypeCacheService.deleteById(id);
        return ResultUtil.success(id);
    }

}
