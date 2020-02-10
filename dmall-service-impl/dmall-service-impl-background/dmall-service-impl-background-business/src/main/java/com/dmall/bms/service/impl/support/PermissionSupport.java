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
     * 根据权限code查询实体
     */
    public PermissionDO getByCode(String code) {
        return permissionMapper.selectOne(Wrappers.<PermissionDO>lambdaQuery()
                .eq(PermissionDO::getCode, code));
    }

    /**
     * 根据uri和method查询实体
     */
    public PermissionDO getByUriAndMethod(String uri, String method) {
        return permissionMapper.selectOne(Wrappers.<PermissionDO>lambdaQuery()
                .eq(PermissionDO::getUri, uri)
                .eq(PermissionDO::getMethod, method));
    }

    public static LambdaQueryWrapper buildWrapper(Long parentId, String code, String name, String uri, String method ){
        LambdaQueryWrapper<PermissionDO> wrapper = Wrappers.<PermissionDO>lambdaQuery()
                .eq(parentId != null, PermissionDO::getParentId,parentId)
                .like(StrUtil.isNotBlank(code), PermissionDO::getCode, code)
                .like(StrUtil.isNotBlank(name), PermissionDO::getName, name)
                .eq(StrUtil.isNotBlank(uri), PermissionDO::getUri, uri)
                .eq(StrUtil.isNotBlank(method), PermissionDO::getMethod, method);
        return wrapper;
    }
}
