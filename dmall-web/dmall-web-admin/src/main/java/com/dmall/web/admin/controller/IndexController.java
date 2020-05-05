package com.dmall.web.admin.controller;
import cn.hutool.core.collection.CollUtil;
import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.web.admin.feign.MenuFeign;
import com.dmall.web.admin.vo.HomeInfoVO;
import com.dmall.web.admin.vo.IndexVO;
import com.dmall.web.admin.vo.LogoInfoVO;
import com.dmall.web.admin.vo.MenuInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 首页控制器
 * @author: created by hang.yu on 2020/1/5 14:56
 */
@RestController
public class IndexController {

    @Autowired
    private MenuFeign menuFeign;

    @RequestMapping("/index")
    public BaseResult<IndexVO> index() {
        BaseResult<List<MenuTreeResponseDTO>> menuTreeResult = menuFeign.myTree();
        if (!menuTreeResult.getResult()) {
            return ResultUtil.fail(menuTreeResult.getCode(), menuTreeResult.getMsg());
        }
        return ResultUtil.success(buildVo(menuTreeResult.getData()));
    }

    /**
     * 构建返回出参
     */
    private IndexVO buildVo(List<MenuTreeResponseDTO> data) {
        IndexVO indexVO = new IndexVO();
        indexVO.setHomeInfo(buildHomeInfoVO());
        indexVO.setLogoInfo(buildLogoInfoVO());
        indexVO.setMenuInfo(buildMenuInfo(data));
        return indexVO;
    }

    /**
     * 构建HomeInfo
     */
    private HomeInfoVO buildHomeInfoVO() {
        HomeInfoVO homeInfoVO = new HomeInfoVO();
        homeInfoVO.setTitle("首页");
        homeInfoVO.setHref("page/welcome-1.html?t=1");
        return homeInfoVO;
    }

    /**
     * 构建LogoInfo
     */
    private LogoInfoVO buildLogoInfoVO() {
        LogoInfoVO logoInfoVO = new LogoInfoVO();
        logoInfoVO.setTitle("地猫商城");
        logoInfoVO.setImage("images/logo.png");
        return logoInfoVO;
    }

    /**
     * 构建菜单信息
     */
    private List<MenuInfoVO> buildMenuInfo(List<MenuTreeResponseDTO> data) {
        return data.stream().map(menuTreeResponse -> {
            MenuInfoVO menuInfoVO = new MenuInfoVO();
            menuInfoVO.setId(menuTreeResponse.getId());
            menuInfoVO.setPid(menuTreeResponse.getParentId());
            menuInfoVO.setTitle(menuTreeResponse.getName());
            menuInfoVO.setIcon(menuTreeResponse.getIcon());
            menuInfoVO.setHref(menuTreeResponse.getUrl());
            menuInfoVO.setTarget(menuTreeResponse.getTarget());
            if (CollUtil.isNotEmpty(menuTreeResponse.getChild())) {
                menuInfoVO.setChild(buildMenuInfo(menuTreeResponse.getChild()));
            }
            return menuInfoVO;
        }).collect(Collectors.toList());
    }
}
