package com.dmall.oms.feign;

import com.dmall.cart.api.service.CartService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: CartFeign
 * @author: created by hang.yu on 2020/3/28 17:49
 */
@FeignClient(value = "dmall-service-impl-cart")
public interface CartFeign extends CartService {
}
