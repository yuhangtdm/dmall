package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.CategoryProductDO;
import com.dmall.pms.generator.mapper.CategoryProductMapper;
import com.dmall.pms.generator.service.ICategoryProductService;
import org.springframework.stereotype.Service;

/**
 * @description: 分类商品关系表
 * @author: created by hang.yu on 2019-12-26 21:58:17
 */
@Service
public class ICategoryProductServiceImpl extends ServiceImpl<CategoryProductMapper, CategoryProductDO> implements ICategoryProductService {

}
