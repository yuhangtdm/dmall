package com.dmall.bms.feign;

import com.dmall.sso.api.service.AdminLoginService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: AdminLoginFeign
 * @author: created by hang.yu on 2020/5/8 21:42
 */
@FeignClient(value = "dmall-service-impl-sso")
public interface AdminLoginFeign extends AdminLoginService {}
