package com.dmall.bms.service.impl.permission.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.bms.api.enums.AppEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.service.IPermissionService;
import com.dmall.bms.generator.service.IRolePermissionService;
import com.dmall.bms.service.support.PermissionSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.swagger.SwaggerInfo;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @description: PermissionImportAllHandler
 * @author: created by hang.yu on 2020/5/16 16:31
 */
@Slf4j
@Component
public class PermissionImportAllHandler extends AbstractCommonHandler<Void, PermissionDO, Void> {

    private static final String URL = "http://dmall-framework-zuul:7010/{}/v2/api-docs";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private PermissionSupport permissionSupport;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Override
    public BaseResult processor(Void aVoid) {
        List<PermissionDO> permissionList = Lists.newArrayList();
        for (AppEnum value : AppEnum.values()) {
            ResponseEntity<SwaggerInfo> forEntity = null;
            try {
                forEntity = restTemplate.getForEntity(StrUtil.format(URL, value.getCode()), SwaggerInfo.class);
            } catch (RestClientException e) {
                log.warn(e.getMessage());
                continue;
            }
            if (forEntity.getStatusCode() == HttpStatus.OK) {
                SwaggerInfo body = forEntity.getBody();
                if (body != null && body.getPaths() != null) {
                    body.getPaths().forEach((k, v) -> {

                        if (v.getGet() != null) {
                            PermissionDO get = permissionSupport.getByUriAndMethod(value.getCode(), k, HttpMethod.GET.name());
                            if (get == null) {
                                PermissionDO permissionDO = new PermissionDO();
                                permissionDO.setAppId(value.getCode());
                                permissionDO.setBusiness(v.getGet().getTags().get(0));
                                permissionDO.setName(v.getGet().getSummary());
                                permissionDO.setUri(k);
                                permissionDO.setMethod(HttpMethod.GET.name());
                                permissionList.add(permissionDO);
                            }

                        }
                        if (v.getPost() != null) {
                            PermissionDO post = permissionSupport.getByUriAndMethod(value.getCode(), k, HttpMethod.POST.name());
                            if (post == null) {
                                PermissionDO permissionDO = new PermissionDO();
                                permissionDO.setAppId(value.getCode());
                                permissionDO.setBusiness(v.getPost().getTags().get(0));
                                permissionDO.setName(v.getPost().getSummary());
                                permissionDO.setUri(k);
                                permissionDO.setMethod(HttpMethod.POST.name());
                                permissionList.add(permissionDO);
                            }
                        }
                        if (v.getDelete() != null) {
                            PermissionDO delete = permissionSupport.getByUriAndMethod(value.getCode(), k, HttpMethod.DELETE.name());
                            if (delete == null) {
                                PermissionDO permissionDO = new PermissionDO();
                                permissionDO.setAppId(value.getCode());
                                permissionDO.setBusiness(v.getDelete().getTags().get(0));
                                permissionDO.setName(v.getDelete().getSummary());
                                permissionDO.setUri(k);
                                permissionDO.setMethod(HttpMethod.DELETE.name());
                                permissionList.add(permissionDO);
                            }
                        }
                        if (v.getPut() != null) {
                            PermissionDO put = permissionSupport.getByUriAndMethod(value.getCode(), k, HttpMethod.PUT.name());
                            if (put == null) {
                                PermissionDO permissionDO = new PermissionDO();
                                permissionDO.setAppId(value.getCode());
                                permissionDO.setBusiness(v.getPut().getTags().get(0));
                                permissionDO.setName(v.getPut().getSummary());
                                permissionDO.setUri(k);
                                permissionDO.setMethod(HttpMethod.PUT.name());
                                permissionList.add(permissionDO);
                            }
                        }
                    });
                }
            }
        }
        permissionService.saveBatch(permissionList);
        List<RolePermissionDO> rolePermissionList = Lists.newArrayList();
        for (PermissionDO permissionDO : permissionList) {
            RolePermissionDO rolePermissionDO = new RolePermissionDO();
            // 超级管理员
            rolePermissionDO.setRoleId(1L);
            rolePermissionDO.setPermissionId(permissionDO.getId());
            rolePermissionList.add(rolePermissionDO);
        }
        rolePermissionService.saveBatch(rolePermissionList);
        return ResultUtil.success();
    }
}
