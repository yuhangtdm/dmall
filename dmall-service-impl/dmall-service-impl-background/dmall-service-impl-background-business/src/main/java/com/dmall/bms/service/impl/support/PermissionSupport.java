package com.dmall.bms.service.impl.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: PermissionSupport
 * @author: created by hang.yu on 2020/1/16 23:28
 */
@Component
public class PermissionSupport {

    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * 根据uri和method查询实体
     */
    public PermissionDO getByUriAndMethod(String appId, String uri, String method) {
        return permissionMapper.selectOne(Wrappers.<PermissionDO>lambdaQuery()
                .eq(PermissionDO::getAppId, appId)
                .eq(PermissionDO::getUri, uri)
                .eq(PermissionDO::getMethod, method));
    }

    /**
     * 构建公共的wrapper
     */
    public static LambdaQueryWrapper buildWrapper(String appId, String name, String uri, String method) {
        return Wrappers.<PermissionDO>lambdaQuery()
                .eq(StrUtil.isNotBlank(appId), PermissionDO::getAppId, appId)
                .like(StrUtil.isNotBlank(name), PermissionDO::getName, name)
                .eq(StrUtil.isNotBlank(uri), PermissionDO::getUri, uri)
                .eq(StrUtil.isNotBlank(method), PermissionDO::getMethod, method);
    }
}
