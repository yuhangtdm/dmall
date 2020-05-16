package com.dmall.bms.service.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.permission.response.tab.BusinessDTO;
import com.dmall.bms.api.dto.permission.response.tab.PermissionDTO;
import com.dmall.bms.api.dto.permission.response.tab.TabPermissionResponseDTO;
import com.dmall.bms.api.enums.AppEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.common.util.EnumUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public static LambdaQueryWrapper buildWrapper(String appId, String business, String name, String uri, String method) {
        return Wrappers.<PermissionDO>lambdaQuery()
                .eq(StrUtil.isNotBlank(appId), PermissionDO::getAppId, appId)
                .like(StrUtil.isNotBlank(business), PermissionDO::getBusiness, business)
                .like(StrUtil.isNotBlank(name), PermissionDO::getName, name)
                .eq(StrUtil.isNotBlank(uri), PermissionDO::getUri, uri)
                .eq(StrUtil.isNotBlank(method), PermissionDO::getMethod, method);
    }

    public static List<TabPermissionResponseDTO> getTabPermissionResponseDTOS(Map<String, List<PermissionDO>> collect, List<Long> permissionIds) {
        List<TabPermissionResponseDTO> result = Lists.newArrayList();
        collect.forEach((k, v) -> {
            TabPermissionResponseDTO response = new TabPermissionResponseDTO();
            response.setAppId(k);
            response.setAppName(EnumUtil.getDesc(AppEnum.class, k));
            List<BusinessDTO> businessList = Lists.newArrayList();
            Map<String, List<PermissionDO>> businessMap = v.stream().collect(Collectors
                    .groupingBy(PermissionDO::getBusiness, Collectors.toList()));
            businessMap.forEach((b, l) -> {
                BusinessDTO business = new BusinessDTO();
                business.setName(b);
                List<PermissionDTO> permissions = l.stream().map(permission -> {
                    PermissionDTO permissionDTO = new PermissionDTO();
                    permissionDTO.setId(permission.getId());
                    permissionDTO.setName(permission.getName());
                    permissionDTO.setChecked(permissionIds.contains(permission.getId()));
                    return permissionDTO;
                }).collect(Collectors.toList());
                business.setPermissions(permissions);
                businessList.add(business);
            });
            response.setBusinessList(businessList);
            result.add(response);
        });
        return result;
    }

}
