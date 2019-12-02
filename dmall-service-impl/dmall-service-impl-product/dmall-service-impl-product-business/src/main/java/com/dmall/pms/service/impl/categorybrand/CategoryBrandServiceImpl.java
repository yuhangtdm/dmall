package com.dmall.pms.service.impl.categorybrand;

import com.dmall.pms.api.dto.categorybrand.request.SaveCategoryBrandRequestDTO;
import com.dmall.pms.api.dto.categorybrand.request.UpdateCategoryBrandRequestDTO;
import com.dmall.pms.api.dto.categorybrand.request.ListCategoryBrandRequestDTO;
import com.dmall.pms.api.dto.categorybrand.request.PageCategoryBrandRequestDTO;
import com.dmall.pms.api.dto.categorybrand.common.CommonCategoryBrandResponseDTO;
import com.dmall.pms.api.service.CategoryBrandService;
import com.dmall.pms.service.impl.categorybrand.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 分类品牌关系服务实现
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@RestController
public class  CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    protected SaveCategoryBrandHandler saveCategoryBrandHandler;

    @Autowired
    private DeleteCategoryBrandHandler deleteCategoryBrandHandler;

    @Autowired
    private UpdateCategoryBrandHandler updateCategoryBrandHandler;

    @Autowired
    private GetCategoryBrandHandler getCategoryBrandHandler;

    @Autowired
    private ListCategoryBrandHandler listCategoryBrandHandler;

    @Autowired
    private PageCategoryBrandHandler pageCategoryBrandHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveCategoryBrandRequestDTO requestDTO) {
        return saveCategoryBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteCategoryBrandHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateCategoryBrandRequestDTO requestDTO) {
        return updateCategoryBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonCategoryBrandResponseDTO> get(Long id) {
        return getCategoryBrandHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonCategoryBrandResponseDTO>> list(@RequestBody ListCategoryBrandRequestDTO requestDTO) {
        return listCategoryBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonCategoryBrandResponseDTO>> page(@RequestBody PageCategoryBrandRequestDTO requestDTO) {
        return pageCategoryBrandHandler.handler(requestDTO);
    }

}
