package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.commentreplypraise.request.ListCommentReplyPraiseRequestDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.PageCommentReplyPraiseRequestDTO;
import com.dmall.pms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.SaveCommentReplyPraiseRequestDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.UpdateCommentReplyPraiseRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 回复点赞服务
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Api(tags = "回复点赞服务")
@RequestMapping("/commentReplyPraise")
public interface CommentReplyPraiseService {

    @PostMapping("/")
    @ApiOperation(value = "新增回复点赞")
    BaseResult<Long> save(@Valid @RequestBody SaveCommentReplyPraiseRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除回复点赞")
    @ApiImplicitParam(name = "id", value = "回复点赞id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改回复点赞")
    BaseResult<Long> update(@Valid @RequestBody UpdateCommentReplyPraiseRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询回复点赞")
    @ApiImplicitParam(name = "id", value = "回复点赞id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonCommentReplyPraiseResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "回复点赞列表")
    BaseResult<List<CommonCommentReplyPraiseResponseDTO>> list(@RequestBody ListCommentReplyPraiseRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "回复点赞分页")
    BaseResult<LayuiPage<CommonCommentReplyPraiseResponseDTO>> page(@RequestBody PageCommentReplyPraiseRequestDTO requestDTO);

}
