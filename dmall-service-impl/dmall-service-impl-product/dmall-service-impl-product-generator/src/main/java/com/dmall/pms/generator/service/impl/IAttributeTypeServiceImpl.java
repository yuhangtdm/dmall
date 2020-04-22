package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.service.IAttributeTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 属性类别表 服务实现类
 * </p>
 *
 * @author hang.yu
 * @since 2019-11-24
 */
@Service
public class IAttributeTypeServiceImpl extends ServiceImpl<AttributeTypeMapper, AttributeTypeDO> implements IAttributeTypeService {

}
