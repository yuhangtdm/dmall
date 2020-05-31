package com.dmall.pms.service.impl.brand;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.CheckedDTO;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.dto.UploadResult;
import com.dmall.pms.api.dto.brand.request.*;
import com.dmall.pms.api.dto.brand.response.BrandResponseDTO;
import com.dmall.pms.api.service.BrandService;
import com.dmall.pms.service.impl.brand.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @description: 品牌服务实现
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@RestController
public class BrandServiceImpl implements BrandService {

    @Autowired
    private SaveBrandHandler saveBrandHandler;

    @Autowired
    private DeleteBrandHandler deleteBrandHandler;

    @Autowired
    private UpdateBrandHandler updateBrandHandler;

    @Autowired
    private GetBrandHandler getBrandHandler;

    @Autowired
    private ListBrandHandler listBrandHandler;

    @Autowired
    private PageBrandHandler pageBrandHandler;

    @Autowired
    private UploadLogoHandler uploadLogoHandler;

    @Autowired
    private SetCategoryHandler setCategoryHandler;

    @Autowired
    private GetBrandCategoryHandler getBrandCategoryHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveBrandRequestDTO requestDTO) {
        return saveBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteBrandHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateBrandRequestDTO requestDTO) {
        return updateBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<BrandResponseDTO> get(Long id) {
        return getBrandHandler.handler(id);
    }

    @Override
    public BaseResult<List<BrandResponseDTO>> list(@RequestBody ListBrandRequestDTO requestDTO) {
        return listBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<ResponsePage<BrandResponseDTO>> page(@RequestBody PageBrandRequestDTO requestDTO) {
        return pageBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UploadResult> uploadLogo(MultipartFile file) {
        return uploadLogoHandler.handler(file);
    }

    @Override
    public BaseResult<Void> setCategory(@RequestBody CheckedDTO requestDTO) {
        return setCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<String>> getCategory(Long id) {
        return getBrandCategoryHandler.handler(id);
    }

}
