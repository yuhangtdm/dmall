package com.dmall.pms.mock.feign;

import com.dmall.mms.api.service.member.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: created by yuhang on 2019/10/15 22:41
 */
@FeignClient(value = "dmall-service-impl-member", fallbackFactory = MemberFallbackFactory.class )
public interface MemberFeignService extends MemberService {
}
