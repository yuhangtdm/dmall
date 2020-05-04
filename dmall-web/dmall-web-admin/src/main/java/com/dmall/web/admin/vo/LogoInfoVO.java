package com.dmall.web.admin.vo;

import lombok.Data;

/**
 * @description: logo信息
 * @author: created by hang.yu on 2020/4/26 23:05
 */
@Data
public class LogoInfoVO {

    /**
     * logo名称
     */
    private String title;

    /**
     * logo图片位置
     */
    private String image;

    /**
     * logo图片地址
     */
    private String href;
}
