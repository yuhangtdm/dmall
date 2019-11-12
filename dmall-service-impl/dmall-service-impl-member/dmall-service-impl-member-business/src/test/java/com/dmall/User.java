package com.dmall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: created by yuhang on 2019/11/12 0:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private Integer age;
}
