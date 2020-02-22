package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.membercollectionsku.request.ListMemberCollectionSkuRequestDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.PageMemberCollectionSkuRequestDTO;
import com.dmall.mms.api.dto.membercollectionsku.common.CommonMemberCollectionSkuResponseDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.SaveMemberCollectionSkuRequestDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.UpdateMemberCollectionSkuRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收藏sku服务
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Api(tags = "会员收藏sku服务")
@RequestMapping("/memberCollectionSku")
public interface MemberCollectionSkuService {

    @PostMapping
    @ApiOperation(value = "新增会员收藏sku")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberCollectionSkuRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员收藏sku")
    @ApiImplicitParam(name = "id", value = "会员收藏skuid", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改会员收藏sku")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberCollectionSkuRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员收藏sku")
    @ApiImplicitParam(name = "id", value = "会员收藏skuid", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberCollectionSkuResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员收藏sku列表")
    BaseResult<List<CommonMemberCollectionSkuResponseDTO>> list(@RequestBody ListMemberCollectionSkuRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员收藏sku分页")
    BaseResult<LayUiPage<CommonMemberCollectionSkuResponseDTO>> page(@RequestBody PageMemberCollectionSkuRequestDTO requestDTO);

}
