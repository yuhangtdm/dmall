package com.dmall.pms.business.service.category;

import com.dmall.common.model.result.BaseResult;
import com.dmall.pms.api.dto.category.common.CategoryResponseDTO;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.UpdateCategoryRequestDTO;
import com.dmall.pms.api.dto.category.response.ZTreeCategoryResponseDTO;
import com.dmall.pms.api.service.CategoryService;
import com.dmall.pms.business.service.category.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商品分类实现
 * @author: created by hang.yu on 2019/11/24 14:23
 */
@RestController
@Controller
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private SaveCategoryHandler saveCategoryHandler;

    @Autowired
    private UpdateCategoryHandler updateCategoryHandler;

    @Autowired
    private ListCategoryHandler listCategoryHandler;

    @Autowired
    private DeleteCategoryHandler deleteCategoryHandler;

    @Autowired
    private GetCategoryHandler getCategoryHandler;

    @Autowired
    private ZTreeCategoryHandler zTreeCategoryHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveCategoryRequestDTO requestDTO) {
        return saveCategoryHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> update(@RequestBody UpdateCategoryRequestDTO requestDTO) {
        return updateCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<CategoryResponseDTO>> list(@RequestBody ListCategoryRequestDTO requestDTO) {
        return listCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteCategoryHandler.handler(id);
    }

    @Override
    public BaseResult<CategoryResponseDTO> get(Long id) {
        return getCategoryHandler.handler(id);
    }

    @Override
    public BaseResult<List<ZTreeCategoryResponseDTO>> zTree(Long parentId) {
        return zTreeCategoryHandler.handler(parentId);
    }

}
