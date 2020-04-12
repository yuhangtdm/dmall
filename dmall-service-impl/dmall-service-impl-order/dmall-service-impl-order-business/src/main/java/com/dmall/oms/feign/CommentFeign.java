package com.dmall.oms.feign;

import com.dmall.pms.api.service.CommentService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: CommentFeign
 * @author: created by hang.yu on 2020/4/12 15:46
 */
@FeignClient(value = "dmall-service-impl-product")
public interface CommentFeign extends CommentService {
}
