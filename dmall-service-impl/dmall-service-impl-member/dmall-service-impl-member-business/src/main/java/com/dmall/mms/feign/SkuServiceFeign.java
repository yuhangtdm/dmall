package com.dmall.mms.feign;

import com.dmall.pms.api.service.SkuService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: SkuServiceFeign
 * @author: created by hang.yu on 2020/2/29 17:16
 */
@FeignClient(value = "dmall-service-impl-product")
public interface SkuServiceFeign  extends SkuService {
}
