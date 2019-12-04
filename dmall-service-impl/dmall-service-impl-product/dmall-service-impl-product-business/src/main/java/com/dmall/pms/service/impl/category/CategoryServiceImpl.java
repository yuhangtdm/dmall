package com.dmall.pms.service.impl.category;

import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.setbrand.SetBrandRequestDTO;
import com.dmall.pms.api.dto.category.request.UpdateCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.api.dto.category.common.CommonCategoryResponseDTO;
import com.dmall.pms.api.dto.category.response.ZTreeCategoryResponseDTO;
import com.dmall.pms.api.service.CategoryService;
import com.dmall.pms.service.impl.category.handler.*;
import com.dmall.common.model.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商品分类服务实现
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@RestController
public class  CategoryServiceImpl implements CategoryService {

    @Autowired
    protected SaveCategoryHandler saveCategoryHandler;

    @Autowired
    private DeleteCategoryHandler deleteCategoryHandler;

    @Autowired
    private UpdateCategoryHandler updateCategoryHandler;

    @Autowired
    private GetCategoryHandler getCategoryHandler;

    @Autowired
    private ListCategoryHandler listCategoryHandler;


    @Autowired
    private ZTreeCategoryHandler zTreeCategoryHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveCategoryRequestDTO requestDTO) {
        return saveCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteCategoryHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateCategoryRequestDTO requestDTO) {
        return updateCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonCategoryResponseDTO> get(Long id) {
        return getCategoryHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonCategoryResponseDTO>> list(@RequestBody ListCategoryRequestDTO requestDTO) {
        return listCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<ZTreeCategoryResponseDTO>> zTree(Long parentId) {
        return zTreeCategoryHandler.handler(parentId);
    }

    @Override
    public BaseResult<Void> setBrand(@Valid SetBrandRequestDTO requestDTO) {
        return null;
    }
}
