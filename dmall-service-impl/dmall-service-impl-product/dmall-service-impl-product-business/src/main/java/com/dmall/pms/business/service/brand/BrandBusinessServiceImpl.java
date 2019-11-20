package com.dmall.pms.business.service.brand;

import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.save.SaveBrandRequestDTO;
import com.dmall.pms.api.service.brand.BrandBusinessService;
import com.dmall.pms.business.service.brand.handler.SaveProductHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 品牌服务实现类
 * @author: created by yuhang on 2019/11/19 23:18
 */
@RestController
public class BrandBusinessServiceImpl implements BrandBusinessService {

    @Autowired
    private SaveProductHandler saveBrandHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveBrandRequestDTO requestDTO) {
        return ResultUtil.success(saveBrandHandler.save(requestDTO));
    }

}
