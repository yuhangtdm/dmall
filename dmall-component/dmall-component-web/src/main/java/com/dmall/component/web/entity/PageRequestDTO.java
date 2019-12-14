package com.dmall.component.web.entity;

import com.dmall.common.enums.OrderByEnum;
import com.dmall.component.web.validate.ValueInEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: page公共请求实体
 * @author: created by hang.yu on 2019/11/23 11:31
 */
@Data
public class PageRequestDTO implements Serializable {

    private static final long serialVersionUID = 5911137056674141847L;

    @ApiModelProperty(value = "当前页码", required = true, position = 1)
    @NotNull(message = "当前页码不能为空")
    @Min(value = 1 ,message = "当前页码必须大于1")
    private Long current;

    @ApiModelProperty(value = "每页记录数", required = true, position = 2)
    @NotNull(message = "每页记录数不能为空")
    @Min(value = 1,message = "每页记录数必须大于1")
    private Long size;

    @ApiModelProperty(value = "排序字段", position = 3)
    private String sortField;

    @ApiModelProperty(value = "排序方向, asc:升序,desc:降序", required = true, position = 4)
    @ValueInEnum(OrderByEnum.class)
    private String orderBy;
}
