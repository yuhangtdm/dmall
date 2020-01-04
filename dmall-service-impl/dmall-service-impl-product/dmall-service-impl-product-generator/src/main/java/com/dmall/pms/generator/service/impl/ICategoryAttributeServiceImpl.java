package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.mapper.CategoryAttributeMapper;
import com.dmall.pms.generator.service.ICategoryAttributeService;
import org.springframework.stereotype.Service;

/**
 * @description: 分类属性表 商品分类和商品属性的中间表
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Service
public class ICategoryAttributeServiceImpl extends ServiceImpl<CategoryAttributeMapper, CategoryAttributeDO> implements ICategoryAttributeService {

}
