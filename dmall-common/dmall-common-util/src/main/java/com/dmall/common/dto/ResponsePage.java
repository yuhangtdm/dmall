package com.dmall.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: ResponsePage
 * @author: created by hang.yu on 2019/11/23 10:21
 */
@Data
public class ResponsePage<T> {

    /**
     * 返回码,默认0为成功
     */
    @ApiModelProperty(value = "返回码", position = 1)
    private Integer code = 0;

    @ApiModelProperty(value = "返回消息", position = 2)
    private String msg = "请求成功";

    @ApiModelProperty(value = "返回总记录数", position = 3)
    private Long count;

    @ApiModelProperty(value = "返回数据", position = 4)
    private List<T> data;

    public ResponsePage(Long count, List<T> data) {
        this.count = count;
        this.data = data;
    }
}
