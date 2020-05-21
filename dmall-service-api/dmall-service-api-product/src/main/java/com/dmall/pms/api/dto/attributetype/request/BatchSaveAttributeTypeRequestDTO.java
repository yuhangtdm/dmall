package com.dmall.pms.api.dto.attributetype.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 批量新增属性类别请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@ApiModel(value = "BatchSaveAttributeTypeRequestDTO", description = "批量新增属性类别请求实体")
public class BatchSaveAttributeTypeRequestDTO implements Serializable {

    private static final long serialVersionUID = 7821691533714257307L;

    @ApiModelProperty(value = "商品分类id", required = true, position = 1)
    @NotNull(message = "商品分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "展示名称列表", required = true, position = 1)
    @NotNull(message = "展示名称列表不能为空")
    @Size(min = 1, message = "展示名称列表不能为空")
    private List<String> showNames;
}
