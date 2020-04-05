package com.dmall.oms.feign;

import com.dmall.sso.api.service.AdminPermissionService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: RoleFeign
 * @author: created by hang.yu on 2020/4/5 12:26
 */
@FeignClient(value = "dmall-service-impl-sso")
public interface RoleFeign extends AdminPermissionService {
}
