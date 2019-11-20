package com.dmall.pms.api.dto.brand.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 新增品牌请求入参
 * @author: created by yuhang on 2019/11/19 22:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "新增品牌实体")
public class SaveBrandRequestDTO implements Serializable {
    private static final long serialVersionUID = 2837808283931901588L;

    /**
     * 品牌名称
     */
    @ApiModelProperty("品牌名称")
    @NotBlank(message = "品牌名称不能为空")
    private String name;

    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    private String englishName;

    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    @NotBlank(message = "首字母不能为空")
    @Length(max = 1,min = 1,message = "首字母长度有一位")
    private String firstLetter;

    /**
     * logo
     */
    @ApiModelProperty("品牌logo")
    private String logo;

    /**
     * 品牌大图
     */
    @ApiModelProperty("品牌大图")
    private String bigPic;
}
