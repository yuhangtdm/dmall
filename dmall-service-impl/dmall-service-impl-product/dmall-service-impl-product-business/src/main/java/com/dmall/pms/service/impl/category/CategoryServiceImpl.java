package com.dmall.pms.service.impl.category;

import com.dmall.common.dto.BaseResult;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.UpdateCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.setattribute.SetAttributeRequestDTO;
import com.dmall.pms.api.dto.category.response.CategoryResponseDTO;
import com.dmall.pms.api.dto.category.response.CategoryTreeResponseDTO;
import com.dmall.pms.api.service.CategoryService;
import com.dmall.pms.service.impl.category.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商品分类服务实现
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@RestController
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private SaveCategoryHandler saveCategoryHandler;

    @Autowired
    private DeleteCategoryHandler deleteCategoryHandler;

    @Autowired
    private UpdateCategoryHandler updateCategoryHandler;

    @Autowired
    private GetCategoryHandler getCategoryHandler;

    @Autowired
    private ListCategoryHandler listCategoryHandler;

    @Autowired
    private CategoryTreeHandler categoryTreeHandler;

    @Autowired
    private SetBrandHandler setBrandHandler;

    @Autowired
    private SetAttributeTypeHandler setAttributeTypeHandler;

    @Autowired
    private SetAttributeHandler setAttributeHandler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> save(@RequestBody SaveCategoryRequestDTO requestDTO) {
        return saveCategoryHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> delete(Long id) {
        return deleteCategoryHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateCategoryRequestDTO requestDTO) {
        return updateCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CategoryResponseDTO> get(Long id) {
        return getCategoryHandler.handler(id);
    }

    @Override
    public BaseResult<List<CategoryResponseDTO>> list(@RequestBody ListCategoryRequestDTO requestDTO) {
        return listCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<CategoryTreeResponseDTO>> tree(Long parentId) {
        return categoryTreeHandler.handler(parentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Void> setAttribute(@RequestBody SetAttributeRequestDTO requestDTO) {
        return setAttributeHandler.handler(requestDTO);
    }
}
