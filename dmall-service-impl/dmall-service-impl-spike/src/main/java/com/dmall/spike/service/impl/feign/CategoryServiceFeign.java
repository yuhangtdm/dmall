package com.dmall.spike.service.impl.feign;

import com.dmall.pms.api.service.CategoryService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 17:00
 */
@FeignClient(value = "dmall-service-impl-product")
public interface CategoryServiceFeign extends CategoryService {
}
