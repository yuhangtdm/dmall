package com.dmall.web.admin.feign;

import com.dmall.bms.api.service.MenuService;
import com.dmall.component.rbac.shiro.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: MenuFeign
 * @author: created by hang.yu on 2020/5/5 21:22
 */
@FeignClient(value = "dmall-service-impl-background", configuration = FeignRequestConfiguration.class)
public interface MenuFeign extends MenuService {
}
