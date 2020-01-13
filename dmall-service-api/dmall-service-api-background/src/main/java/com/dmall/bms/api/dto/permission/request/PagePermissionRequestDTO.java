package com.dmall.bms.api.dto.permission.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 资源分页请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PagePermissionRequestDTO", description =  "资源分页请求实体")
public class PagePermissionRequestDTO  extends PageRequestDTO {



        @ApiModelProperty(value = "父级id", position = 2)
        private Integer parentId;

        @ApiModelProperty(value = "权限码", position = 3)
        private String code;

        @ApiModelProperty(value = "权限名称", position = 4)
        private String name;

        @ApiModelProperty(value = "1-接口地址;2-目录;3-菜单", position = 5)
        private Integer type;

        @ApiModelProperty(value = "权限地址", position = 6)
        private String uri;

        private String method;

        @ApiModelProperty(value = "图标", position = 8)
        private String icon;







}
