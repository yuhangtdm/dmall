package com.dmall.web.admin.vo.index;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @description: 菜单对象
 * @author: created by hang.yu on 2020/4/26 23:09
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuInfoVO {

    /**
     * 菜单id
     */
    private Long id;

    /**
     * 菜单父id
     */
    private Long pid;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     *
     */
    private String href;

    /**
     *
     */
    private String target;

    /**
     *
     */
    private List<MenuInfoVO> child;
}
