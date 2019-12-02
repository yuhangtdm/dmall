package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 品牌列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListBrandRequestDTO", description="品牌列表请求实体")
public class ListBrandRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "品牌名称", position = 1)
    private String name;

    @ApiModelProperty(value = "英文名称", position = 2)
    private String englishName;

    @ApiModelProperty(value = "首字母", position = 3)
    @NotBlank(message = "首字母不能为空")
    @Length(max = 1,min = 1,message = "首字母长度固定1位")
    private String firstLetter;
}
