package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import com.dmall.bms.api.dto.menu.response.PageMenuResponseDTO;
import com.dmall.bms.api.dto.menu.request.PageMenuRequestDTO;
import com.dmall.bms.api.dto.menu.response.GetMenuResponseDTO;
import com.dmall.bms.api.dto.menu.request.SaveMenuRequestDTO;
import com.dmall.bms.api.dto.menu.request.UpdateMenuRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 菜单服务
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Api(tags = "菜单服务")
@RequestMapping("/menu")
public interface MenuService {

    @PostMapping
    @ApiOperation(value = "新增菜单")
    BaseResult<Long> save(@Valid @RequestBody SaveMenuRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单表 ")
    @ApiImplicitParam(name = "id", value = "菜单表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改菜单")
    BaseResult<Long> update(@Valid @RequestBody UpdateMenuRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询菜单")
    @ApiImplicitParam(name = "id", value = "菜单表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<GetMenuResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/page")
    @ApiOperation(value = "菜单分页")
    BaseResult<ResponsePage<PageMenuResponseDTO>> page(@RequestBody PageMenuRequestDTO requestDTO);

    @GetMapping("/tree")
    @ApiOperation(value = "菜单树")
    BaseResult<List<MenuTreeResponseDTO>> tree();

    @GetMapping("/myTree")
    @ApiOperation(value = "当前用户的菜单树")
    BaseResult<List<MenuTreeResponseDTO>> myTree();

}
