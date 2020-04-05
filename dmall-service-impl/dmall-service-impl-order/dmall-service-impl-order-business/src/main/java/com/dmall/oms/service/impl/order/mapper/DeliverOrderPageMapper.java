package com.dmall.oms.service.impl.order.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.oms.api.dto.deliver.DeliverOrderPageRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: DeliverOrderPageMapper
 * @author: created by hang.yu on 2020/4/5 11:31
 */
public interface DeliverOrderPageMapper {

    List<DeliverOrderPageDTO> deliverOrderPage(Page page, @Param("request") DeliverOrderPageRequestDTO requestDTO);
}
