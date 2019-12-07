package com.dmall.pms.api.dto.attribute.request;

import com.dmall.common.enums.base.YNEnum;
import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.attribute.enums.AttributeEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 新增属性请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value="SaveAttributeRequestDTO", description="新增属性请求实体")
public class SaveAttributeRequestDTO{

    @ApiModelProperty(value = "属性分类id", position = 1)
    @NotNull(message = "属性分类id不能为空")
    private Long attributeTypeId;


    @ApiModelProperty(value = "名称", position = 2)
    @NotBlank(message = "名称不能为空")
    private String name;


    @ApiModelProperty(value = "备注", position = 3)
    private String remark;


    @ApiModelProperty(value = "类型 1-规格;2-参数;", position = 4)
    @ValueInEnum(AttributeEnum.class)
    @NotNull(message = "类型不能为空")
    private Integer type;


    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 8)
    @ValueInEnum(InputTypeEnum.class)
    @NotNull(message = "属性录入方式不能为空")
    private Integer inputType;


    @ApiModelProperty(value = "可选值列表 以逗号隔开", position = 9)
    private String inputList;

    @ApiModelProperty(value = "排序", position = 11)
    private Integer sort;


    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 12)
    @ValueInEnum(YNEnum.class)
    @NotNull(message = "是否支持手动新增不能为空")
    private String handAddStatus;

}
