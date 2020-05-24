package com.dmall.common.dto.dtree;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description: DTreeResponseDTO
 * @author: created by hang.yu on 2020/5/24 16:09
 */
@Data
@ApiModel(value = "DTreeResponseDTO", description = "dTree树公共响应实体")
public class DTreeResponseDTO {

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "父id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "节点名称", position = 3)
    private String title;

    @ApiModelProperty(value = "默认是否打开", position = 4)
    private Boolean spread;

    @ApiModelProperty(value = "是否为父元素", position = 5)
    private Boolean parent;

    @ApiModelProperty(value = "是否禁用", position = 6)
    private Boolean disabled;

    @ApiModelProperty(value = "自定义数据", position = 7)
    private Map<String, Object> basicData;

    @ApiModelProperty(value = "复选框集合", position = 8)
    private List<CheckArr> checkArr;

    @ApiModelProperty(value = "子节点集合", position = 9)
    private List<DTreeResponseDTO> children;
}
