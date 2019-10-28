package com.dmall.service.impl.business;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: created by yuhang on 2019/10/24 22:22
 */
@RestController
public class RoleService {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
