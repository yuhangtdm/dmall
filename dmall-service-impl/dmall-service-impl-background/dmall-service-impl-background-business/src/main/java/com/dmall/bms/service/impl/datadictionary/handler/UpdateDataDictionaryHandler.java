package com.dmall.bms.service.impl.datadictionary.handler;

import com.dmall.bms.api.dto.datadictionary.request.UpdateDataDictionaryRequestDTO;
import com.dmall.bms.generator.dataobject.DataDictionaryDO;
import com.dmall.bms.generator.mapper.DataDictionaryMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改数据字典处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class UpdateDataDictionaryHandler extends AbstractCommonHandler<UpdateDataDictionaryRequestDTO, DataDictionaryDO, Long> {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public BaseResult<Long> validate(UpdateDataDictionaryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateDataDictionaryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}