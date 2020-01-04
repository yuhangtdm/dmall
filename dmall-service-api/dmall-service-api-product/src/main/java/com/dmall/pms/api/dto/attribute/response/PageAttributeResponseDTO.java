package com.dmall.pms.api.dto.attribute.response;

import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description: 属性分页响应实体
 * @author: created by hang.yu on 2019/12/24 22:28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PageAttributeResponseDTO" , description = "属性分页响应实体" )
public class PageAttributeResponseDTO {

    @ApiModelProperty(value = "id" , position = 1)
    private Long id;

    @ApiModelProperty(value = "名称" , position = 2)
    private String name;

    @ApiModelProperty(value = "展示名称" , position = 3)
    private String showName;

    @ApiModelProperty(value = "类型" , position = 4)
    private TypeEnum type;

    @ApiModelProperty(value = "属性录入方式" , position = 5)
    private InputTypeEnum inputType;

    @ApiModelProperty(value = "可选值列表 以逗号隔开" , position = 6)
    private String inputList;

    @ApiModelProperty(value = "是否支持手动新增" , position = 7)
    private HandAddStatusEnum handAddStatus;

    @ApiModelProperty(value = "创建人" , position = 9)
    private Long creator;

    @ApiModelProperty(value = "创建时间" , position = 10)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人" , position = 11)
    private Long modifier;

    @ApiModelProperty(value = "更新时间" , position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用" , position = 13)
    private String isDeleted;
}
