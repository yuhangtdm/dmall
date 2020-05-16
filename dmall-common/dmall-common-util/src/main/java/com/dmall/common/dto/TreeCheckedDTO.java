package com.dmall.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @description: TreeCheckedDTO
 * @author: created by hang.yu on 2020/5/16 10:51
 */
@Data
@ApiModel(value = "TreeCheckedDTO", description = "树的复选框选中请求实体")
public class TreeCheckedDTO implements Serializable {

    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "关联id集合", required = true, position = 2)
    @NotNull(message = "关联id集合不能为空")
    @Size(min = 1, message = "关联id集合不能为空")
    private Set<Long> relateIds;

}
