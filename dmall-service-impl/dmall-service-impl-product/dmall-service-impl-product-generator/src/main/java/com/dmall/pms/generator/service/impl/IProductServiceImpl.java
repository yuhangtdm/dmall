package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.service.IProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author hang.yu
 * @since 2019-11-24
 */
@Service
public class IProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements IProductService {

}
