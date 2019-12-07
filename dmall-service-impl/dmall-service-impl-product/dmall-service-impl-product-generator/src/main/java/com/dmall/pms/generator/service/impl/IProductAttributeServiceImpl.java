package com.dmall.pms.generator.service.impl;

import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.mapper.ProductAttributeMapper;
import com.dmall.pms.generator.service.IProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 属性值表
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Service
public class IProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeDO> implements IProductAttributeService {

}
