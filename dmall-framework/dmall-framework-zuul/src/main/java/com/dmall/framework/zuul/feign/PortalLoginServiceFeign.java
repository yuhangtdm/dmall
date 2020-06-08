package com.dmall.framework.zuul.feign;

import com.dmall.sso.api.service.PortalLoginService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: PortalLoginServiceFeign
 * @author: created by hang.yu on 2020/2/26 16:41
 */
@FeignClient(value = "dmall-service-impl-sso")
public interface PortalLoginServiceFeign extends PortalLoginService {}
