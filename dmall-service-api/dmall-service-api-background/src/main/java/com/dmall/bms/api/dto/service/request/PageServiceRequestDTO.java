package com.dmall.bms.api.dto.service.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 服务表 分页请求实体
 * @author: created by hang.yu on 2020-02-20 21:36:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageServiceRequestDTO", description =  "服务表 分页请求实体")
public class PageServiceRequestDTO  extends PageRequestDTO {



        @ApiModelProperty(value = "app_id", position = 2)
        private String appId;

        @ApiModelProperty(value = "名称", position = 3)
        private String name;







}
