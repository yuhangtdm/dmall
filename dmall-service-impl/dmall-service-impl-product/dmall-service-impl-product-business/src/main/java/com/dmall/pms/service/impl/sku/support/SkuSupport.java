package com.dmall.pms.service.impl.sku.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 12:42
 */
@Component
public class SkuSupport {

    @Autowired
    private SkuMapper skuMapper;

    public List<SkuDO> selectByProductId(Long productId) {
        return skuMapper.selectList(Wrappers.<SkuDO>lambdaQuery()
                .eq(SkuDO::getProductId, productId));
    }
}
