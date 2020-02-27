package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.commentreply.request.ListCommentReplyRequestDTO;
import com.dmall.mms.api.dto.commentreply.request.PageCommentReplyRequestDTO;
import com.dmall.mms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.mms.api.dto.commentreply.request.SaveCommentReplyRequestDTO;
import com.dmall.mms.api.dto.commentreply.request.UpdateCommentReplyRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 评价回复服务
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Api(tags = "评价回复服务")
@RequestMapping("/commentReply")
public interface CommentReplyService {

    @PostMapping
    @ApiOperation(value = "新增评价回复")
    BaseResult<Long> save(@Valid @RequestBody SaveCommentReplyRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除评价回复")
    @ApiImplicitParam(name = "id", value = "评价回复id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改评价回复")
    BaseResult<Long> update(@Valid @RequestBody UpdateCommentReplyRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询评价回复")
    @ApiImplicitParam(name = "id", value = "评价回复id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonCommentReplyResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "评价回复列表")
    BaseResult<List<CommonCommentReplyResponseDTO>> list(@RequestBody ListCommentReplyRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "评价回复分页")
    BaseResult<LayUiPage<CommonCommentReplyResponseDTO>> page(@RequestBody PageCommentReplyRequestDTO requestDTO);

}
