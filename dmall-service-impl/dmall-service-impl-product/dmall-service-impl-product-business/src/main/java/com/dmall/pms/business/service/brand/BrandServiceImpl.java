package com.dmall.pms.business.service.brand;

import com.dmall.common.model.result.BaseResult;
import com.dmall.pms.api.dto.brand.save.SaveBrandRequestDTO;
import com.dmall.pms.api.service.brand.BrandService;
import com.dmall.pms.business.service.brand.handler.SaveBrandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 品牌服务实现类
 * @author: created by yuhang on 2019/11/19 23:18
 */
@RestController
public class BrandServiceImpl implements BrandService {

    @Autowired
    private SaveBrandHandler saveBrandHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveBrandRequestDTO requestDTO) {
        return saveBrandHandler.handler(requestDTO);
    }

}
