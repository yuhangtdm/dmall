package com.dmall.oms.feign;

import com.dmall.pay.api.service.PaymentService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: PayFeign
 * @author: created by hang.yu on 2020/4/8 21:30
 */
@FeignClient(value = "dmall-service-impl-pay")
public interface PaymentFeign extends PaymentService {}
