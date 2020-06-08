package com.dmall.pms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.generator.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: CommentSupport
 * @author: created by hang.yu on 2020/4/25 17:17
 */
@Component
public class CommentSupport {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 根据商品编号查询评价数量
     */
    public Integer countByProductId(Long productId) {
        return commentMapper.selectCount(Wrappers.<CommentDO>lambdaQuery().eq(CommentDO::getProductId, productId));
    }

    /**
     * 根据商品编号查询评价数量
     */
    public Integer countByProductId(Long productId, Integer level) {
        return commentMapper.selectCount(Wrappers.<CommentDO>lambdaQuery()
            .eq(CommentDO::getProductId, productId)
            .eq(CommentDO::getLevel, level));
    }

    /**
     * 根据商品编号查询评价数量
     */
    public Integer countByProductIdHasPic(Long productId) {
        return commentMapper.selectCount(Wrappers.<CommentDO>lambdaQuery()
            .eq(CommentDO::getProductId, productId)
            .isNotNull(CommentDO::getMedias));
    }

    /**
     * 根据skuId查询评价数量
     */
    public Integer countBySkuId(Long skuId) {
        return commentMapper.selectCount(Wrappers.<CommentDO>lambdaQuery().eq(CommentDO::getSkuId, skuId));
    }

    /**
     * 根据skuId查询评价数量
     */
    public Integer countBySkuId(Long skuId, Integer level) {
        return commentMapper.selectCount(Wrappers.<CommentDO>lambdaQuery()
            .eq(CommentDO::getSkuId, skuId)
            .eq(CommentDO::getLevel, level));
    }

    /**
     * 根据skuId查询评价数量
     */
    public Integer countBySkuIdHasPic(Long skuId) {
        return commentMapper.selectCount(Wrappers.<CommentDO>lambdaQuery()
            .eq(CommentDO::getSkuId, skuId)
            .isNotNull(CommentDO::getMedias));
    }
}
