package com.dmall.web.admin.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: created by yuhang on 2019/11/10 15:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {

    private Long id;

    private String name;

    private Integer age;
}
