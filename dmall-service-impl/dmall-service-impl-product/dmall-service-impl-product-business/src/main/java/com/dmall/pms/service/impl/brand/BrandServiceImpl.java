package com.dmall.pms.service.impl.brand;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.common.model.result.UploadResult;
import com.dmall.pms.api.dto.brand.common.CommonBrandResponseDTO;
import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.PageBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.SaveBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.UpdateBrandRequestDTO;
import com.dmall.pms.api.dto.brand.response.PageBrandResponseDTO;
import com.dmall.pms.api.service.BrandService;
import com.dmall.pms.service.impl.brand.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public BaseResult<CommonBrandResponseDTO> get(Long id) {
        return getBrandHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonBrandResponseDTO>> list(@RequestBody ListBrandRequestDTO requestDTO) {
        return listBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<PageBrandResponseDTO>> page(@RequestBody PageBrandRequestDTO requestDTO) {
        return pageBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UploadResult> uploadLogo(MultipartFile file) {
        return uploadLogoHandler.handler(file);
    }


}
