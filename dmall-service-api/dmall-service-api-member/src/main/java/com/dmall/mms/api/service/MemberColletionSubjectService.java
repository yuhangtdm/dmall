package com.dmall.mms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.mms.api.dto.membercolletionsubject.common.CommonMemberColletionSubjectResponseDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.ListMemberColletionSubjectRequestDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.PageMemberColletionSubjectRequestDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.SaveMemberColletionSubjectRequestDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.UpdateMemberColletionSubjectRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收藏专题表 服务
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Api(tags = "会员收藏专题表 服务")
@RequestMapping("/memberColletionSubject")
public interface MemberColletionSubjectService {

    @PostMapping("/")
    @ApiOperation(value = "新增会员收藏专题表 ")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberColletionSubjectRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员收藏专题表 ")
    @ApiImplicitParam(name = "id", value = "会员收藏专题表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改会员收藏专题表 ")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberColletionSubjectRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员收藏专题表 ")
    @ApiImplicitParam(name = "id", value = "会员收藏专题表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberColletionSubjectResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员收藏专题表 列表")
    BaseResult<List<CommonMemberColletionSubjectResponseDTO>> list(@RequestBody ListMemberColletionSubjectRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员收藏专题表 分页")
    BaseResult<LayuiPage<CommonMemberColletionSubjectResponseDTO>> page(@RequestBody PageMemberColletionSubjectRequestDTO requestDTO);

}
