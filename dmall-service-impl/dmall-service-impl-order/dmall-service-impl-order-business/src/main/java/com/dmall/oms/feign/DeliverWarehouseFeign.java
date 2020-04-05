package com.dmall.oms.feign;

import com.dmall.bms.api.service.DeliverWarehouseService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: DeliverWarehouseFeign
 * @author: created by hang.yu on 2020/4/5 17:00
 */
@FeignClient(value = "dmall-service-impl-background")
public interface DeliverWarehouseFeign extends DeliverWarehouseService {
}
