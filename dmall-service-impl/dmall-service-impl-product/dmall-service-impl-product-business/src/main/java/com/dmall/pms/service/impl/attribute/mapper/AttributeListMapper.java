package com.dmall.pms.service.impl.attribute.mapper;

import com.dmall.pms.api.dto.attribute.request.ListAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: AttributeListMapper
 * @author: created by hang.yu on 2019/12/24 23:29
 */
public interface AttributeListMapper {

    List<AttributeDO> listAttribute(@Param("request") ListAttributeRequestDTO requestDTO);
}
