package com.dmall.oms.api.dto.suborderjob.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 子订单job表 公共响应实体
 * @author: created by hang.yu on 2020-04-18 21:31:26
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonSubOrderJobResponseDTO", description = "子订单job表 公共响应实体")
public class CommonSubOrderJobResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "sub_order_id", position = 2)
    private Long subOrderId;

    @ApiModelProperty(value = "xxl_job_id", position = 3)
    private Integer xxlJobId;

    @ApiModelProperty(value = "type 1-自动收货;2-自动好评", position = 4)
    private Integer type;

    @ApiModelProperty(value = "创建人", position = 5)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 6)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 7)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 8)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 9)
    private String isDeleted;


}
