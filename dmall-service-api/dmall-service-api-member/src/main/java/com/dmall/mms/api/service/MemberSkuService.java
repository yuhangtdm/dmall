package com.dmall.mms.api.service;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.ResponsePage;
import com.dmall.mms.api.dto.membercollectionsku.request.SaveMemberCollectionSkuRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.membersku.response.PageMemberCollectionSkuResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员和sku服务
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Api(tags = "会员和sku服务")
@RequestMapping("/memberSku")
public interface MemberSkuService {

    @PostMapping("/collection/page")
    @ApiOperation(value = "会员收藏sku分页")
    BaseResult<ResponsePage<PageMemberCollectionSkuResponseDTO>> collectionPage(PageRequestDTO requestDTO);

    @GetMapping("/collection/{skuId}")
    @ApiOperation(value = "收藏sku")
    @ApiImplicitParam(name = "id", value = "skuId", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> collectionSku(@PathVariable("skuId") Long skuId);

    @DeleteMapping("/collection/{id}")
    @ApiOperation(value = "取消收藏sku")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> cancel(@PathVariable("id") Long id);

}
