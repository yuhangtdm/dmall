package com.dmall.oms.service.impl.order.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.oms.api.dto.myaftersalepage.MyAfterSalePageResponseDTO;
import com.dmall.oms.service.impl.order.mapper.dto.MyAfterSalePageDbDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: MyAfterSalePageMapper
 * @author: created by hang.yu on 2020/4/16 22:53
 */
public interface MyAfterSalePageMapper {

    List<MyAfterSalePageResponseDTO> myAfterSalePage(Page page, @Param(("request")) MyAfterSalePageDbDTO requestDTO);
}
