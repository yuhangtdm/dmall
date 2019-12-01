package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.membersafe.request.ListMemberSafeRequestDTO;
import com.dmall.mms.api.dto.membersafe.request.PageMemberSafeRequestDTO;
import com.dmall.mms.api.dto.membersafe.common.CommonMemberSafeResponseDTO;
import com.dmall.mms.api.dto.membersafe.request.SaveMemberSafeRequestDTO;
import com.dmall.mms.api.dto.membersafe.request.UpdateMemberSafeRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 账户安全服务
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Api(tags = "账户安全服务")
@RequestMapping("/memberSafe")
public interface MemberSafeService {

    @PostMapping("/")
    @ApiOperation(value = "新增账户安全")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberSafeRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除账户安全")
    @ApiImplicitParam(name = "id", value = "账户安全id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改账户安全")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberSafeRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询账户安全")
    @ApiImplicitParam(name = "id", value = "账户安全id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberSafeResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "账户安全列表")
    BaseResult<List<CommonMemberSafeResponseDTO>> list(@RequestBody ListMemberSafeRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "账户安全分页")
    BaseResult<LayuiPage<CommonMemberSafeResponseDTO>> page(@RequestBody PageMemberSafeRequestDTO requestDTO);

}
