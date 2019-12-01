package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.member.request.ListMemberRequestDTO;
import com.dmall.mms.api.dto.member.request.PageMemberRequestDTO;
import com.dmall.mms.api.dto.member.common.CommonMemberResponseDTO;
import com.dmall.mms.api.dto.member.request.SaveMemberRequestDTO;
import com.dmall.mms.api.dto.member.request.UpdateMemberRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员服务
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Api(tags = "会员服务")
@RequestMapping("/member")
public interface MemberService {

    @PostMapping("/")
    @ApiOperation(value = "新增会员")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员")
    @ApiImplicitParam(name = "id", value = "会员id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改会员")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员")
    @ApiImplicitParam(name = "id", value = "会员id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员列表")
    BaseResult<List<CommonMemberResponseDTO>> list(@RequestBody ListMemberRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员分页")
    BaseResult<LayuiPage<CommonMemberResponseDTO>> page(@RequestBody PageMemberRequestDTO requestDTO);

}
