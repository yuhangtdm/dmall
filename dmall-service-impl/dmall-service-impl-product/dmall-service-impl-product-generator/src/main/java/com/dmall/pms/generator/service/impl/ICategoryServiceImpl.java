package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.generator.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author hang.yu
 * @since 2019-11-24
 */
@Service
public class ICategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryDO> implements ICategoryService {

}
