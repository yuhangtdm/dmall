package com.dmall.oms.service.impl.order;

import com.dmall.common.dto.BaseResult;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import com.dmall.oms.api.service.OrderService;
import com.dmall.oms.service.impl.order.handler.ToTradeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/26 23:02
 */
@RestController
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ToTradeHandler toTradeHandler;

    @Override
    public BaseResult<ToTradeResponseDTO> toTrade(@RequestBody ToTradeRequestDTO requestDTO) {
        return toTradeHandler.handler(requestDTO);
    }
}
