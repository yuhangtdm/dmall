package com.dmall.oms.api.dto.aftersaledetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 售后单日志实体
 * @author: created by hang.yu on 2020/4/16 22:25
 */
@Data
@ApiModel(value = "AfterSaleLogDTO", description = "售后单日志实体")
public class AfterSaleLogDTO implements Serializable {

    private static final long serialVersionUID = 8099181513321987957L;

    @ApiModelProperty(value = "logId", position = 1)
    private Long logId;

    @ApiModelProperty(value = "日志类型 1-会员;2-系统", position = 2)
    private Integer logType;

    @ApiModelProperty(value = "日志标题", position = 3)
    private String logTitle;

    @ApiModelProperty(value = "日志内容", position = 4)
    private String logContent;

    @ApiModelProperty(value = "日志内容", position = 5)
    private Date createTime;
}
