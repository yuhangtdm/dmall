package com.dmall.pms.api.service.brand;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.brand.list.ListBrandRequestDTO;
import com.dmall.pms.api.dto.brand.page.BrandPageRequestDTO;
import com.dmall.pms.api.dto.brand.common.BrandCommonResponseDTO;
import com.dmall.pms.api.dto.brand.save.SaveBrandCommonRequestDTO;
import com.dmall.pms.api.dto.brand.update.UpdateBrandCommonRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 品牌服务
 * @author: created by hang.yu on 2019/11/19 22:46
 */
@Api(tags = "品牌管理")
@RequestMapping("/brand")
public interface BrandService {

    @ApiOperation(value = "新增品牌")
    @PostMapping("/")
    BaseResult<Long> save(@Valid @RequestBody SaveBrandCommonRequestDTO requestDTO);

    @ApiOperation(value = "修改品牌")
    @PutMapping("/")
    BaseResult<Long> update(@Valid @RequestBody UpdateBrandCommonRequestDTO requestDTO);

    @ApiOperation(value = "品牌分页")
    @PostMapping("/page")
    BaseResult<LayuiPage<BrandCommonResponseDTO>> page(@RequestBody BrandPageRequestDTO pageRequestDTO);

    @ApiOperation(value = "品牌列表")
    @PostMapping("/list")
    BaseResult<List<BrandCommonResponseDTO>> list(@RequestBody ListBrandRequestDTO pageRequestDTO);

    @ApiOperation(value = "删除品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{id}")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @ApiOperation(value = "根据id查询品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{id}")
    BaseResult<Long> get(@PathVariable("id") Long id);

}
