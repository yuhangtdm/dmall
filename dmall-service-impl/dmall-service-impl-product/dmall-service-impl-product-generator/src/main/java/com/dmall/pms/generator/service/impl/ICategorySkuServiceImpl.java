package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.CategorySkuDO;
import com.dmall.pms.generator.mapper.CategorySkuMapper;
import com.dmall.pms.generator.service.ICategorySkuService;
import org.springframework.stereotype.Service;

/**
 * @description: 分类sku关系表
 * @author: created by hang.yu on 2019-12-23 21:27:18
 */
@Service
public class ICategorySkuServiceImpl extends ServiceImpl<CategorySkuMapper, CategorySkuDO> implements ICategorySkuService {

}
