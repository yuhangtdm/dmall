package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ObjectUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attribute.request.PageAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.response.AttributeResponseDTO;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.service.impl.attribute.mapper.AttributePageMapper;
import com.dmall.pms.service.support.CategorySupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 属性分页处理器
 * @author: created by hang.yu on 2019-12-16 15:14:49
 */
@Component
public class PageAttributeHandler
    extends AbstractCommonHandler<PageAttributeRequestDTO, AttributeDO, AttributeResponseDTO> {

    @Autowired
    private AttributePageMapper attributePageMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private CategorySupport categorySupport;

    @Override
    public BaseResult<ResponsePage<AttributeResponseDTO>> processor(PageAttributeRequestDTO requestDTO) {
        if (requestDTO.getCategoryId() != null) {
            CategoryDO categoryDO = pmsValidate.validateCategory(requestDTO.getCategoryId());
            // 一级分类
            if (LevelEnum.ONE.getCode().equals(categoryDO.getLevel())) {
                LambdaQueryWrapper<AttributeDO> wrapper = Wrappers.<AttributeDO>lambdaQuery()
                    .eq(AttributeDO::getCategoryId, requestDTO.getCategoryId())
                    .like(StrUtil.isNotBlank(requestDTO.getShowName()), AttributeDO::getShowName,
                        requestDTO.getShowName())
                    .eq(ObjectUtil.isNotEmpty(requestDTO.getInputType()), AttributeDO::getInputType,
                        requestDTO.getInputType())
                    .eq(ObjectUtil.isNotEmpty(requestDTO.getHandAddStatus()), AttributeDO::getHandAddStatus,
                        requestDTO.getHandAddStatus());
                IPage<AttributeDO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
                page = attributeMapper.selectPage(page, wrapper);
                List<AttributeResponseDTO> collect = page.getRecords().stream()
                    .map(attributeDO -> doConvertDto(attributeDO, AttributeResponseDTO.class))
                    .collect(Collectors.toList());
                return ResultUtil.success(new ResponsePage<>(page.getTotal(), collect));
            }
        }
        Page<AttributeResponseDTO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        List<AttributeResponseDTO> collect = attributePageMapper.pageAttribute(page, requestDTO).stream()
            .map(attributeDO -> {
                AttributeResponseDTO attributeResponse = doConvertDto(attributeDO, AttributeResponseDTO.class);
                // 查询的为三级分类 修改为一级分类后返回
                if (attributeResponse.getCategoryId() != null) {
                    attributeResponse
                        .setCategoryId(categorySupport.getId(attributeResponse.getCategoryId(), LevelEnum.ONE));
                }
                return attributeResponse;
            }).collect(Collectors.toList());
        page.setRecords(collect);
        return ResultUtil.success(new ResponsePage<>(page.getTotal(), page.getRecords()));
    }

}
