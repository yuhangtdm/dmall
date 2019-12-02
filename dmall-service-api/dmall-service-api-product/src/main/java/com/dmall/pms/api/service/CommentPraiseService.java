package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.commentpraise.request.ListCommentPraiseRequestDTO;
import com.dmall.pms.api.dto.commentpraise.request.PageCommentPraiseRequestDTO;
import com.dmall.pms.api.dto.commentpraise.common.CommonCommentPraiseResponseDTO;
import com.dmall.pms.api.dto.commentpraise.request.SaveCommentPraiseRequestDTO;
import com.dmall.pms.api.dto.commentpraise.request.UpdateCommentPraiseRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 评论点赞数服务
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Api(tags = "评论点赞数服务")
@RequestMapping("/commentPraise")
public interface CommentPraiseService {

    @PostMapping("/")
    @ApiOperation(value = "新增评论点赞数")
    BaseResult<Long> save(@Valid @RequestBody SaveCommentPraiseRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除评论点赞数")
    @ApiImplicitParam(name = "id", value = "评论点赞数id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改评论点赞数")
    BaseResult<Long> update(@Valid @RequestBody UpdateCommentPraiseRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询评论点赞数")
    @ApiImplicitParam(name = "id", value = "评论点赞数id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonCommentPraiseResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "评论点赞数列表")
    BaseResult<List<CommonCommentPraiseResponseDTO>> list(@RequestBody ListCommentPraiseRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "评论点赞数分页")
    BaseResult<LayuiPage<CommonCommentPraiseResponseDTO>> page(@RequestBody PageCommentPraiseRequestDTO requestDTO);

}
