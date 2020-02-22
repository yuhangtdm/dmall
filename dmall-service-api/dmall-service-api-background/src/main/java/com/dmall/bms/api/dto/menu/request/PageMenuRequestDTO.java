package com.dmall.bms.api.dto.menu.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 菜单表 分页请求实体
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageMenuRequestDTO", description =  "菜单表 分页请求实体")
public class PageMenuRequestDTO  extends PageRequestDTO {

   @ApiModelProperty(value = "名称", position = 1)
   private String name;

   @ApiModelProperty(value = "上级id", position = 2)
   private Long parentId;

   @ApiModelProperty(value = "类型 1-目录;2-菜单", position = 3)
   private Integer type;

   @ApiModelProperty(value = "地址", position = 4)
   private String url;

}
