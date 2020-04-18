package com.dmall.oms.api.dto.suborderjob.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 子订单job表 列表请求实体
 * @author: created by hang.yu on 2020-04-18 21:31:26
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListSubOrderJobRequestDTO", description = "子订单job表 列表请求实体")
public class ListSubOrderJobRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "sub_order_id", position = 2)
        private Long subOrderId;

        @ApiModelProperty(value = "xxl_job_id", position = 3)
        private Integer xxlJobId;

        @ApiModelProperty(value = "type 1-自动收货;2-自动好评", position = 4)
        private Integer type;







}
