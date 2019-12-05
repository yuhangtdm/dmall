package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.common.CommonCategoryResponseDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商品分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class GetCategoryHandler extends AbstractCommonHandler<Long, CategoryDO, CommonCategoryResponseDTO> {


    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult<CommonCategoryResponseDTO> processor(Long id) {
        CategoryDO categoryDO = categoryCacheService.selectById(id);
        if (categoryDO == null){
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(categoryDO,CommonCategoryResponseDTO.class));
    }

    public String getCascadeCategoryName(Long categoryId){
        CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
        StringBuilder sb = new StringBuilder();
        StrUtil.splitTrim(categoryDO.getPath(), CharUtil.DOT).stream().map(id ->Long.valueOf(id)).forEach(id ->{
            sb.append(categoryCacheService.selectById(id).getName()).append(StrUtil.SLASH);
        });
       return sb.deleteCharAt(sb.length()-1).toString();
    }

}
