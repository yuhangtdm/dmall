package com.dmall.pms.service.impl.attribute.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.request.PageAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.response.PageAttributeResponseDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.service.impl.attribute.mapper.AttributePageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 属性分页处理器
 * @author: created by hang.yu on 2019-12-16 15:14:49
 */
@Component
public class PageAttributeHandler extends AbstractCommonHandler<PageAttributeRequestDTO, AttributeDO, PageAttributeResponseDTO> {

    @Autowired
    private AttributePageMapper attributePageMapper;

    @Override
    public BaseResult<LayuiPage<PageAttributeResponseDTO>> validate(PageAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<PageAttributeResponseDTO>> processor(PageAttributeRequestDTO requestDTO) {
        //todo 待测试
        Page<PageAttributeResponseDTO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        page.setRecords(attributePageMapper.pageAttribute(page, requestDTO));
        return ResultUtil.success(new LayuiPage<>(page.getTotal(), page.getRecords()));
    }

}
