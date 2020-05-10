package com.dmall.web.admin.vo.index;

import lombok.Data;

import java.util.List;

/**
 * @description: 初始化数据
 * @author: created by hang.yu on 2020/4/26 23:05
 */
@Data
public class IndexVO {

    private HomeInfoVO homeInfo;

    private LogoInfoVO logoInfo;

    private UserInfoVO userInfo;

    private List<MenuInfoVO> menuInfo;
}
