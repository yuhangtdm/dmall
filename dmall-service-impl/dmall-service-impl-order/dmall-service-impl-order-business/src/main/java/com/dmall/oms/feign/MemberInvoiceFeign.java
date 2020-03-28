package com.dmall.oms.feign;

import com.dmall.mms.api.service.MemberInvoiceService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: MemberAddressFeign
 * @author: created by hang.yu on 2020/3/26 23:28
 */
@FeignClient(value = "dmall-service-impl-member")
public interface MemberInvoiceFeign extends MemberInvoiceService {
}
