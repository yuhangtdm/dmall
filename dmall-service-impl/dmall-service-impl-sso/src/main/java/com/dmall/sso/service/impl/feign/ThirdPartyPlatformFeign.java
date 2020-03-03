package com.dmall.sso.service.impl.feign;

import com.dmall.mms.api.service.ThirdPartyPlatformService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: ThirdPartyPlatformFeign
 * @author: created by hang.yu on 2020/3/1 21:49
 */
@FeignClient("dmall-service-impl-member")
public interface ThirdPartyPlatformFeign extends ThirdPartyPlatformService {
}
