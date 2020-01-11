package com.dmall.framework.zuul.feign;

import com.dmall.sso.api.service.AdminLoginService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: LoginServiceFeign
 * @author: created by hang.yu on 2020/1/6 23:04
 */
@FeignClient(value = "dmall-service-impl-sso")
public interface LoginServiceFeign extends AdminLoginService {

}
