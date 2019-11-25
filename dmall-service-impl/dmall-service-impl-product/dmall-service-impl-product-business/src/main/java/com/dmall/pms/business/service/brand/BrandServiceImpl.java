package com.dmall.pms.business.service.brand;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.BrandPageRequestDTO;
import com.dmall.pms.api.dto.brand.common.BrandCommonResponseDTO;
import com.dmall.pms.api.dto.brand.request.SaveBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.UpdateBrandRequestDTO;
import com.dmall.pms.api.service.BrandService;
import com.dmall.pms.business.service.brand.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 品牌服务实现类
 * @author: created by hang.yu on 2019/11/19 23:18
 */
@RestController
public class BrandServiceImpl implements BrandService {

    @Autowired
    protected SaveBrandHandler saveBrandHandler;

    @Autowired
    private UpdateBrandHandler updateBrandHandler;

    @Autowired
    private PageBrandHandler pageBrandHandler;

    @Autowired
    private ListBrandHandler listBrandHandler;

    @Autowired
    private DeleteBrandHandler deleteBrandHandler;

    @Autowired
    private GetBrandHandler getBrandHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveBrandRequestDTO requestDTO) {
        return saveBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateBrandRequestDTO requestDTO) {
        return updateBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<BrandCommonResponseDTO>> page(@RequestBody BrandPageRequestDTO requestDTO) {
        return pageBrandHandler.handler(requestDTO);
    }

    @Override
//    @MapCacheable
    public BaseResult<List<BrandCommonResponseDTO>> list(@RequestBody ListBrandRequestDTO requestDTO) {
        return listBrandHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteBrandHandler.handler(id);
    }

    @Override
    public BaseResult<BrandCommonResponseDTO> get(Long id) {
        return getBrandHandler.handler(id);
    }

}
