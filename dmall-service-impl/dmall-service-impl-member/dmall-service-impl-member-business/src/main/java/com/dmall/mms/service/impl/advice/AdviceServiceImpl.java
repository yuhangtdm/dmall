package com.dmall.mms.service.impl.advice;

import com.dmall.mms.api.dto.advice.request.SaveAdviceRequestDTO;
import com.dmall.mms.api.dto.advice.request.UpdateAdviceRequestDTO;
import com.dmall.mms.api.dto.advice.request.ListAdviceRequestDTO;
import com.dmall.mms.api.dto.advice.request.PageAdviceRequestDTO;
import com.dmall.mms.api.dto.advice.common.CommonAdviceResponseDTO;
import com.dmall.mms.api.service.AdviceService;
import com.dmall.mms.service.impl.advice.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 会员意见表 服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:02
 */
@RestController
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private SaveAdviceHandler saveAdviceHandler;

    @Autowired
    private DeleteAdviceHandler deleteAdviceHandler;

    @Autowired
    private UpdateAdviceHandler updateAdviceHandler;

    @Autowired
    private GetAdviceHandler getAdviceHandler;

    @Autowired
    private ListAdviceHandler listAdviceHandler;

    @Autowired
    private PageAdviceHandler pageAdviceHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveAdviceRequestDTO requestDTO) {
        return saveAdviceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteAdviceHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateAdviceRequestDTO requestDTO) {
        return updateAdviceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonAdviceResponseDTO> get(Long id) {
        return getAdviceHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonAdviceResponseDTO>> list(@RequestBody ListAdviceRequestDTO requestDTO) {
        return listAdviceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonAdviceResponseDTO>> page(@RequestBody PageAdviceRequestDTO requestDTO) {
        return pageAdviceHandler.handler(requestDTO);
    }

}
