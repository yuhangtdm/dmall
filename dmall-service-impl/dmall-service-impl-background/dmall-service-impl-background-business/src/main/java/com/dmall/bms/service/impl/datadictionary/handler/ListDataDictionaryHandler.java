package com.dmall.bms.service.impl.datadictionary.handler;

import com.dmall.bms.api.dto.datadictionary.common.CommonDataDictionaryResponseDTO;
import com.dmall.bms.api.dto.datadictionary.request.ListDataDictionaryRequestDTO;
import com.dmall.bms.generator.dataobject.DataDictionaryDO;
import com.dmall.bms.generator.mapper.DataDictionaryMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 数据字典列表处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class ListDataDictionaryHandler extends AbstractCommonHandler<ListDataDictionaryRequestDTO, DataDictionaryDO, CommonDataDictionaryResponseDTO> {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public BaseResult<List<CommonDataDictionaryResponseDTO>> validate(ListDataDictionaryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonDataDictionaryResponseDTO>> processor(ListDataDictionaryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
