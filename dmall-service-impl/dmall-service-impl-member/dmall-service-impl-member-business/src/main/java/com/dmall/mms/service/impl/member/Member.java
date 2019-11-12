package com.dmall.mms.service.impl.member;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: created by yuhang on 2019/11/10 15:10
 */
@Data
public class Member implements Serializable {

    private Long id;

    private String name;
}
