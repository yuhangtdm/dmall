package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.userloginlog.request.ListUserLoginLogRequestDTO;
import com.dmall.bms.api.dto.userloginlog.request.PageUserLoginLogRequestDTO;
import com.dmall.bms.api.dto.userloginlog.common.CommonUserLoginLogResponseDTO;
import com.dmall.bms.api.dto.userloginlog.request.SaveUserLoginLogRequestDTO;
import com.dmall.bms.api.dto.userloginlog.request.UpdateUserLoginLogRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台用户登录日志服务
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Api(tags = "后台用户登录日志服务")
@RequestMapping("/userLoginLog")
public interface UserLoginLogService {

    @PostMapping("/")
    @ApiOperation(value = "新增后台用户登录日志")
    BaseResult<Long> save(@Valid @RequestBody SaveUserLoginLogRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台用户登录日志")
    @ApiImplicitParam(name = "id", value = "后台用户登录日志id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改后台用户登录日志")
    BaseResult<Long> update(@Valid @RequestBody UpdateUserLoginLogRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台用户登录日志")
    @ApiImplicitParam(name = "id", value = "后台用户登录日志id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonUserLoginLogResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台用户登录日志列表")
    BaseResult<List<CommonUserLoginLogResponseDTO>>list(@RequestBody ListUserLoginLogRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台用户登录日志分页")
    BaseResult<LayUiPage<CommonUserLoginLogResponseDTO>>page(@RequestBody PageUserLoginLogRequestDTO requestDTO);

}
