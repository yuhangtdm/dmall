package com.dmall.pms.generator.service.impl;

import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author yuhang
 * @since 2019-11-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {

}
