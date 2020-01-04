package com.dmall.mms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.mms.api.dto.memberloginlog.common.CommonMemberLoginLogResponseDTO;
import com.dmall.mms.api.dto.memberloginlog.request.ListMemberLoginLogRequestDTO;
import com.dmall.mms.api.dto.memberloginlog.request.PageMemberLoginLogRequestDTO;
import com.dmall.mms.api.dto.memberloginlog.request.SaveMemberLoginLogRequestDTO;
import com.dmall.mms.api.dto.memberloginlog.request.UpdateMemberLoginLogRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员登录记录服务
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Api(tags = "会员登录记录服务" )
@RequestMapping("/memberLoginLog" )
public interface MemberLoginLogService {

    @PostMapping("/" )
    @ApiOperation(value = "新增会员登录记录" )
    BaseResult<Long> save(@Valid @RequestBody SaveMemberLoginLogRequestDTO requestDTO);

    @DeleteMapping("/{id}" )
    @ApiOperation(value = "删除会员登录记录" )
    @ApiImplicitParam(name = "id" , value = "会员登录记录id" , required = true, dataType = "int" , paramType = "path" )
    BaseResult<Long> delete(@PathVariable("id" ) Long id);

    @PutMapping("/" )
    @ApiOperation(value = "修改会员登录记录" )
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberLoginLogRequestDTO requestDTO);

    @GetMapping("/{id}" )
    @ApiOperation(value = "根据id查询会员登录记录" )
    @ApiImplicitParam(name = "id" , value = "会员登录记录id" , required = true, dataType = "int" , paramType = "path" )
    BaseResult<CommonMemberLoginLogResponseDTO> get(@PathVariable("id" ) Long id);

    @PostMapping("/list" )
    @ApiOperation(value = "会员登录记录列表" )
    BaseResult<List<CommonMemberLoginLogResponseDTO>> list(@RequestBody ListMemberLoginLogRequestDTO requestDTO);

    @PostMapping("/page" )
    @ApiOperation(value = "会员登录记录分页" )
    BaseResult<LayuiPage<CommonMemberLoginLogResponseDTO>> page(@RequestBody PageMemberLoginLogRequestDTO requestDTO);

}
