package com.dmall.cart.feign;

import com.dmall.pms.api.service.SkuService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: SkuFeign
 * @author: created by hang.yu on 2020/3/14 14:48
 */
@FeignClient(value = "dmall-service-impl-product")
public interface SkuFeign extends SkuService {
}
