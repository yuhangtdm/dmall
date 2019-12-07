package com.dmall.pms.api.dto.attribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.attribute.common.CommonAttributeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

/**
 * @description: 修改属性请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value="UpdateAttributeRequestDTO", description="修改属性请求实体")
public class UpdateAttributeRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;

    @ApiModelProperty(value = "类型 1-规格;2-参数;", position = 4)
    private Integer type;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 5)
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表 以逗号隔开", position = 6)
    private String inputList;


    @ApiModelProperty(value = "排序", position = 8)
    private Integer sort;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 9)
    private String handAddStatus;

}
