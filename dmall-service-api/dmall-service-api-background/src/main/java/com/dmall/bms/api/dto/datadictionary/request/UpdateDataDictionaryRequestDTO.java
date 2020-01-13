package com.dmall.bms.api.dto.datadictionary.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.datadictionary.common.CommonDataDictionaryRequestDTO;

/**
 * @description: 修改数据字典请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateDataDictionaryRequestDTO", description = "修改数据字典请求实体")
public class UpdateDataDictionaryRequestDTO extends CommonDataDictionaryRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
