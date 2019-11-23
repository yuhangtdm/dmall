package com.dmall.pms.api.dto.brand.common;

import com.dmall.common.enums.base.IsDeletedEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @description: 品牌列表返回实体
 * @author: created by hang.yu on 2019/11/23 10:30
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BrandPageResponseDTO", description = "AllArgsConstructor")
@EqualsAndHashCode(callSuper = false)
public class BrandCommonResponseDTO extends BrandCommonRequestDTO {

    private static final long serialVersionUID = 7256425409372270661L;

    @ApiModelProperty(value = "品牌id", position = 1)
    private Long id;

    @ApiModelProperty(value = "创建人", position = 7)
    private Long creator;

    @ApiModelProperty(value = "创建人姓名", position = 8)
    private String creatorName;

    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;

    @ApiModelProperty(value = "修改人", position = 10)
    private Long modifier;

    @ApiModelProperty(value = "修改人姓名", position = 11)
    private String modifierName;

    @ApiModelProperty(value = "修改时间", position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态", position = 13)
    private String isDeleted;
}
