package com.dmall.pms.service.impl.brand.cache;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import com.dmall.component.cache.redis.mapcache.MapDeleteCache;
import com.dmall.component.cache.redis.mapcache.MapGetCache;
import com.dmall.component.cache.redis.mapcache.MapPutCache;
import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: Brand缓存操作
 * @author: created by hang.yu on 2019/12/3 22:18
 */
@Component
public class BrandCacheService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询列表
     */
    @MapCacheable(cacheNames = "brand")
    public List<BrandDO> selectList(ListBrandRequestDTO requestDTO) {
        LambdaQueryWrapper<BrandDO> queryWrapper = Wrappers.<BrandDO>lambdaQuery()
                .like(StrUtil.isNotBlank(requestDTO.getEnglishName()), BrandDO::getEnglishName, requestDTO.getEnglishName())
                .like(StrUtil.isNotBlank(requestDTO.getName()), BrandDO::getName, requestDTO.getName())
                .eq(StrUtil.isNotBlank(requestDTO.getFirstLetter()), BrandDO::getFirstLetter, requestDTO.getFirstLetter());
        return brandMapper.selectList(queryWrapper);
    }

    /**
     * 新增
     */
    @MapPutCache(cacheNames = "brand")
    public int insert(BrandDO brandDO) {
        return brandMapper.insert(brandDO);
    }

    /**
     * 修改
     */
    @MapPutCache(cacheNames = "brand")
    public int updateById(BrandDO brandDO) {
        return brandMapper.updateById(brandDO);
    }

    /**
     * 删除
     */
    @MapDeleteCache(cacheNames = "brand")
    public int deleteById(Long id) {
        return brandMapper.deleteById(id);
    }

    /**
     * 查询对象
     */
    @MapGetCache(cacheNames = "brand")
    public BrandDO selectById(Long id) {
        return brandMapper.selectById(id);
    }
}
