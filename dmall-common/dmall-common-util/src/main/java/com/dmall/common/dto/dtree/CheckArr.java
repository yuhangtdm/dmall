package com.dmall.common.dto.dtree;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: CheckArr
 * @author: created by hang.yu on 2020/5/24 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CheckArr", description = "dtree是否选中实体")
public class CheckArr {

    /**
     * 复选框标记
     */
    private String type;
    /**
     * 复选框是否选中
     */
    private String checked;
}
