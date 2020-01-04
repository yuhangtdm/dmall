package com.dmall.pms.service.impl.attribute.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.pms.api.dto.attribute.request.PageAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: AttributePageMapper
 * @author: created by hang.yu on 2019/12/24 23:29
 */
public interface AttributePageMapper {

    List<AttributeDO> pageAttribute(Page page, @Param("request" ) PageAttributeRequestDTO requestDTO);
}
