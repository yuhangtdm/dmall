package com.dmall.oms.feign;

import com.dmall.pms.api.service.SkuService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: SkuFeign
 * @author: created by hang.yu on 2020/3/26 23:25
 */
@FeignClient(value = "dmall-service-impl-product")
public interface SkuFeign extends SkuService {}
