package com.dmall.search.service.feign;

import com.dmall.oms.api.service.SellerOrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: SellerOrderFeign
 * @author: created by hang.yu on 2020/4/25 16:35
 */
@FeignClient(value = "dmall-service-impl-order")
public interface SellerOrderFeign extends SellerOrderService {
}
