package com.dmall.pms.service.impl.category.support;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: CategorySupport
 * @author: created by hang.yu on 2019/12/7 21:44
 */
@Component
public class CategorySupport {

    @Autowired
    private CategoryCacheService categoryCacheService;

    /**
     * 获取级联的分类名称
     */
    public String getCascadeCategoryName(Long categoryId){
        CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
        StringBuilder sb = new StringBuilder();
        StrUtil.splitTrim(categoryDO.getPath(), CharUtil.DOT).stream().map(Long::valueOf).forEach(id ->{
            sb.append(categoryCacheService.selectById(id).getName()).append(StrUtil.SLASH);
        });
        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
