package com.dmall.pms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.pms.api.dto.comment.request.PageCommentRequestDTO;
import com.dmall.pms.api.dto.comment.request.SaveCommentRequestDTO;
import com.dmall.pms.api.dto.comment.response.CommentPageResponseDTO;
import com.dmall.pms.api.dto.comment.response.CommentResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商品评价服务
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Api(tags = "商品评价服务")
@RequestMapping("/comment")
public interface CommentService {

    @PostMapping
    @ApiOperation(value = "新增商品评价")
    BaseResult<Long> save(@Valid @RequestBody List<SaveCommentRequestDTO> requestDTO);

    @GetMapping("/list/{subOrderId}")
    @ApiImplicitParam(name = "subOrderId", value = "子订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "商品评价列表")
    BaseResult<List<CommentPageResponseDTO>> list(@PathVariable("subOrderId") Long subOrderId);

    @PostMapping("/page")
    @ApiOperation(value = "商品评价分页")
    BaseResult<CommentResponseDTO> page(PageCommentRequestDTO requestDTO);
}
