package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.util.EnumUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ObjectUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import com.dmall.pms.api.dto.attribute.request.PageAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.response.PageAttributeResponseDTO;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.service.impl.attribute.mapper.AttributePageMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 属性分页处理器
 * @author: created by hang.yu on 2019-12-16 15:14:49
 */
@Component
public class PageAttributeHandler extends AbstractCommonHandler<PageAttributeRequestDTO, AttributeDO, PageAttributeResponseDTO> {

    @Autowired
    private AttributePageMapper attributePageMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult<ResponsePage<PageAttributeResponseDTO>> validate(PageAttributeRequestDTO requestDTO) {
        // 分类必须存在 且必须是一级分类
        if (requestDTO.getCategoryId() != null) {
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            if (categoryDO == null) {
                return ResultUtil.fail(AttributeErrorEnum.CATEGORY_NOT_EXIST);
            }
            if (LevelEnum.TWO.getCode().equals(categoryDO.getLevel())) {
                return ResultUtil.fail(AttributeErrorEnum.CATEGORY_NOT_INVALID);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<ResponsePage<PageAttributeResponseDTO>> processor(PageAttributeRequestDTO requestDTO) {
        if (requestDTO.getCategoryId() != null) {
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            // 一级分类
            if (LevelEnum.ONE.getCode().equals(categoryDO.getLevel())) {
                LambdaQueryWrapper<AttributeDO> wrapper = Wrappers.<AttributeDO>lambdaQuery()
                        .likeRight(AttributeDO::getName, categoryDO.getName())
                        .like(StrUtil.isNotBlank(requestDTO.getShowName()), AttributeDO::getShowName, requestDTO.getShowName())
                        .eq(ObjectUtil.isNotEmpty(requestDTO.getType()), AttributeDO::getType, requestDTO.getType())
                        .eq(ObjectUtil.isNotEmpty(requestDTO.getInputType()), AttributeDO::getInputType, requestDTO.getInputType())
                        .eq(ObjectUtil.isNotEmpty(requestDTO.getHandAddStatus()), AttributeDO::getHandAddStatus, requestDTO.getHandAddStatus());
                IPage<AttributeDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
                page = attributeMapper.selectPage(page, wrapper);
                List<PageAttributeResponseDTO> collect = page.getRecords().stream()
                        .map(category -> doConvertDto(category, PageAttributeResponseDTO.class))
                        .collect(Collectors.toList());
                return ResultUtil.success(new ResponsePage<>(page.getTotal(), collect));
            }
        }
        // 三级分类需要连表查询
        Page<PageAttributeResponseDTO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        List<PageAttributeResponseDTO> collect = attributePageMapper.pageAttribute(page, requestDTO).stream()
                .map(category -> doConvertDto(category, PageAttributeResponseDTO.class))
                .collect(Collectors.toList());
        page.setRecords(collect);
        return ResultUtil.success(new ResponsePage<>(page.getTotal(), page.getRecords()));
    }

    @Override
    protected void customerConvertDto(PageAttributeResponseDTO result, AttributeDO doo) {
        result.setInputType(EnumUtil.getKeyValueEnum(InputTypeEnum.class, doo.getInputType()));
        result.setType(EnumUtil.getKeyValueEnum(TypeEnum.class, doo.getType()));
        result.setHandAddStatus(EnumUtil.getKeyValueEnum(HandAddStatusEnum.class, doo.getHandAddStatus()));
    }
}
