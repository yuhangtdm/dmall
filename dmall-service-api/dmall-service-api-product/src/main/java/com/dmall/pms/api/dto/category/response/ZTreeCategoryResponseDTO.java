package com.dmall.pms.api.dto.category.response;

import com.dmall.pms.api.dto.category.common.CategoryResponseDTO;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: ZTree树响应实体
 * @author: created by hang.yu on 2019/11/24 18:30
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ZTreeCategoryResponseDTO", description = "ZTree分类树实体")
@EqualsAndHashCode(callSuper = false)
public class ZTreeCategoryResponseDTO extends CategoryResponseDTO {

    @ApiModelProperty(value = "子元素",  position = 23)
    private List<ZTreeCategoryResponseDTO> children= Lists.newArrayList();

    @ApiModelProperty(value = "是否打开",  position = 24)
    private Boolean open=false;

    @ApiModelProperty(value = "是否为父元素",  position = 25)
    private Boolean isParent=false;
}
