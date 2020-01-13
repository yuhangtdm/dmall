package com.dmall.bms.api.dto.datadictionary.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.datadictionary.common.CommonDataDictionaryRequestDTO;

/**
 * @description: 新增数据字典请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveDataDictionaryRequestDTO", description = "新增数据字典请求实体")
public class SaveDataDictionaryRequestDTO extends CommonDataDictionaryRequestDTO {

}
