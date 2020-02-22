package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.memberstatisticsinfo.request.ListMemberStatisticsInfoRequestDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.PageMemberStatisticsInfoRequestDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.common.CommonMemberStatisticsInfoResponseDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.SaveMemberStatisticsInfoRequestDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.UpdateMemberStatisticsInfoRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员统计信息服务
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Api(tags = "会员统计信息服务")
@RequestMapping("/memberStatisticsInfo")
public interface MemberStatisticsInfoService {

    @PostMapping
    @ApiOperation(value = "新增会员统计信息")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberStatisticsInfoRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员统计信息")
    @ApiImplicitParam(name = "id", value = "会员统计信息id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改会员统计信息")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberStatisticsInfoRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员统计信息")
    @ApiImplicitParam(name = "id", value = "会员统计信息id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberStatisticsInfoResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员统计信息列表")
    BaseResult<List<CommonMemberStatisticsInfoResponseDTO>> list(@RequestBody ListMemberStatisticsInfoRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员统计信息分页")
    BaseResult<LayUiPage<CommonMemberStatisticsInfoResponseDTO>> page(@RequestBody PageMemberStatisticsInfoRequestDTO requestDTO);

}
