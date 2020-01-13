package com.dmall.bms.service.impl.datadictionary;

import com.dmall.bms.api.dto.datadictionary.request.SaveDataDictionaryRequestDTO;
import com.dmall.bms.api.dto.datadictionary.request.UpdateDataDictionaryRequestDTO;
import com.dmall.bms.api.dto.datadictionary.request.ListDataDictionaryRequestDTO;
import com.dmall.bms.api.dto.datadictionary.request.PageDataDictionaryRequestDTO;
import com.dmall.bms.api.dto.datadictionary.common.CommonDataDictionaryResponseDTO;
import com.dmall.bms.api.service.DataDictionaryService;
import com.dmall.bms.service.impl.datadictionary.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 数据字典服务实现
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@RestController
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private SaveDataDictionaryHandler saveDataDictionaryHandler;

    @Autowired
    private DeleteDataDictionaryHandler deleteDataDictionaryHandler;

    @Autowired
    private UpdateDataDictionaryHandler updateDataDictionaryHandler;

    @Autowired
    private GetDataDictionaryHandler getDataDictionaryHandler;

    @Autowired
    private ListDataDictionaryHandler listDataDictionaryHandler;

    @Autowired
    private PageDataDictionaryHandler pageDataDictionaryHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveDataDictionaryRequestDTO requestDTO) {
        return saveDataDictionaryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteDataDictionaryHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateDataDictionaryRequestDTO requestDTO) {
        return updateDataDictionaryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonDataDictionaryResponseDTO> get(Long id) {
        return getDataDictionaryHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonDataDictionaryResponseDTO>> list(@RequestBody ListDataDictionaryRequestDTO requestDTO) {
        return listDataDictionaryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonDataDictionaryResponseDTO>> page(@RequestBody PageDataDictionaryRequestDTO requestDTO) {
        return pageDataDictionaryHandler.handler(requestDTO);
    }

}
