package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改品牌请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@ApiModel(value = "UpdateBrandRequestDTO", description = "修改品牌请求实体")
public class UpdateBrandRequestDTO implements Serializable {

    private static final long serialVersionUID = 4042145404746071053L;

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "品牌id不能为空")
    private Long id;

    @ApiModelProperty(value = "品牌名称", required = true, position = 2)
    @NotBlank(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty(value = "英文名称", position = 3)
    private String englishName;

    @ApiModelProperty(value = "首字母", position = 4)
    @Length(max = 1, min = 1, message = "首字母长度固定一位")
    private String firstLetter;

    @ApiModelProperty(value = "品牌logo", position = 5)
    private String logo;

    @ApiModelProperty(value = "品牌大图", position = 6)
    private String bigPic;

}
