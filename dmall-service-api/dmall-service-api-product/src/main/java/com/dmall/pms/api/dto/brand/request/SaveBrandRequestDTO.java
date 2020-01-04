package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 新增品牌请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SaveBrandRequestDTO" , description = "新增品牌请求实体" )
public class SaveBrandRequestDTO implements Serializable {

    private static final long serialVersionUID = -2764431828668087336L;

    @ApiModelProperty(value = "品牌名称" , required = true, position = 1)
    @NotBlank(message = "品牌名称不能为空" )
    private String name;

    @ApiModelProperty(value = "英文名称" , position = 2)
    private String englishName;

    @ApiModelProperty(value = "首字母" , required = true, position = 3)
    @NotBlank(message = "首字母不能为空" )
    @Length(max = 1, min = 1, message = "首字母长度固定一位" )
    private String firstLetter;

    @ApiModelProperty(value = "品牌logo" , position = 4)
    private String logo;

    @ApiModelProperty(value = "品牌大图" , position = 5)
    private String bigPic;
}
