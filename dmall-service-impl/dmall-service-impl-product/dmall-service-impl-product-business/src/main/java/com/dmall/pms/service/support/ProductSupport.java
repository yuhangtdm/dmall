package com.dmall.pms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: ProductSupport
 * @author: created by hang.yu on 2020/4/25 10:41
 */
@Component
public class ProductSupport {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 根据品牌id获取列表
     */
    public List<ProductDO> listByBrandId(Long brandId) {
        return productMapper.selectList(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getBrandId, brandId));
    }

    /**
     * 根据名称获取记录
     */
    public ProductDO getByName(String name) {
        return productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getName, name));
    }
}
