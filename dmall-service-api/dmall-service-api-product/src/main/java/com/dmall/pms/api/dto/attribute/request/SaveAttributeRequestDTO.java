package com.dmall.pms.api.dto.attribute.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import com.dmall.pms.api.enums.TypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 新增商品属性请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@ApiModel(value = "SaveAttributeRequestDTO", description = "新增商品属性请求实体")
public class SaveAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID = 1744049394685408430L;

    @ApiModelProperty(value = "一级分类id", required = true, position = 1)
    @NotNull(message = "一级分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "展示名称", required = true, position = 2)
    @NotBlank(message = "展示名称不能为空")
    private String showName;

    @ApiModelProperty(value = "属性类型 1-普通属性;2-公共属性", required = true, position = 3)
    @ValueInEnum(TypeEnum.class)
    @NotNull(message = "属性类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", required = true, position = 4)
    @ValueInEnum(InputTypeEnum.class)
    @NotNull(message = "属性录入方式不能为空")
    private Integer inputType;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", required = true, position = 5)
    @ValueInEnum(HandAddStatusEnum.class)
    @NotNull(message = "是否支持手动新增不能为空")
    private String handAddStatus;

    @ApiModelProperty(value = "可选值列表", position = 6)
    private List<String> inputList;

    @ApiModelProperty(value = "备注", position = 7)
    private String remark;

}
