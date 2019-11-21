package com.dmall.pms.api.service.brand;

import com.dmall.common.model.result.BaseResult;
import com.dmall.pms.api.dto.brand.save.SaveBrandRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @description: 品牌服务
 * @author: created by yuhang on 2019/11/19 22:46
 */
@Api(tags = "品牌管理")
@RequestMapping("/brand")
public interface BrandService {

    @ApiOperation(value = "新增品牌")
    @PostMapping("/")
    BaseResult<Long> save(@Valid @RequestBody SaveBrandRequestDTO requestDTO);
}
