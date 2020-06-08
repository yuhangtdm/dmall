package com.dmall.component.rbac.shiro.feign;

import com.dmall.sso.api.service.AdminPermissionService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: AdminPermissionFeign
 * @author: created by hang.yu on 2020/1/12 11:12
 */
@FeignClient(value = "dmall-service-impl-sso")
public interface AdminPermissionFeign extends AdminPermissionService {}
