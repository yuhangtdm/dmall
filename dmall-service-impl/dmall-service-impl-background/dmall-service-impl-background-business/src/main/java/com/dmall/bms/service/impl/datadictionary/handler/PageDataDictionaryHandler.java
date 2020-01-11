package com.dmall.bms.service.impl.datadictionary.handler;

import com.dmall.bms.api.dto.datadictionary.common.CommonDataDictionaryResponseDTO;
import com.dmall.bms.api.dto.datadictionary.request.PageDataDictionaryRequestDTO;
import com.dmall.bms.generator.dataobject.DataDictionaryDO;
import com.dmall.bms.generator.mapper.DataDictionaryMapper;
import com.dmall.common.dto.LayUiPage;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 数据字典分页处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class PageDataDictionaryHandler extends AbstractCommonHandler<PageDataDictionaryRequestDTO, DataDictionaryDO, CommonDataDictionaryResponseDTO> {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public BaseResult<LayUiPage<CommonDataDictionaryResponseDTO>> validate(PageDataDictionaryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonDataDictionaryResponseDTO>> processor(PageDataDictionaryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
