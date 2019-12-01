package com.dmall.pms.api.dto.common;

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

    /**
     * 当前页码 默认1页
     */
    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull(message = "当前页码不能为空")
    @Min(1)
    private Long current = 1L;

    /**
     * 每页记录数 默认10条
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    @NotNull(message = "每页记录数不能为空")
    @Min(1)
    private Long size = 10L;
}
