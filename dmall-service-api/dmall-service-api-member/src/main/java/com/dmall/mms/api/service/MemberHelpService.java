package com.dmall.mms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import com.dmall.mms.api.dto.memberhelp.common.CommonMemberHelpResponseDTO;
import com.dmall.mms.api.dto.memberhelp.request.ListMemberHelpRequestDTO;
import com.dmall.mms.api.dto.memberhelp.request.PageMemberHelpRequestDTO;
import com.dmall.mms.api.dto.memberhelp.request.SaveMemberHelpRequestDTO;
import com.dmall.mms.api.dto.memberhelp.request.UpdateMemberHelpRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员-帮助关系表 帮助对会员有用服务
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Api(tags = "会员-帮助关系表 帮助对会员有用服务")
@RequestMapping("/memberHelp")
public interface MemberHelpService {

    @PostMapping("/")
    @ApiOperation(value = "新增会员-帮助关系表 帮助对会员有用")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberHelpRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员-帮助关系表 帮助对会员有用")
    @ApiImplicitParam(name = "id", value = "会员-帮助关系表 帮助对会员有用id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改会员-帮助关系表 帮助对会员有用")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberHelpRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员-帮助关系表 帮助对会员有用")
    @ApiImplicitParam(name = "id", value = "会员-帮助关系表 帮助对会员有用id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberHelpResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员-帮助关系表 帮助对会员有用列表")
    BaseResult<List<CommonMemberHelpResponseDTO>> list(@RequestBody ListMemberHelpRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员-帮助关系表 帮助对会员有用分页")
    BaseResult<LayUiPage<CommonMemberHelpResponseDTO>> page(@RequestBody PageMemberHelpRequestDTO requestDTO);

}
