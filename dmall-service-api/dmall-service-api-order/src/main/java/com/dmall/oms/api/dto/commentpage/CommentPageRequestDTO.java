package com.dmall.oms.api.dto.commentpage;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.YNEnum;
import com.dmall.oms.api.enums.SubOrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 评价分页请求实体
 * @author: created by hang.yu on 2020/4/11 21:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CommentPageRequestDTO", description = "评价分页请求实体")
public class CommentPageRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "评价状态", position = 1)
    @ValueInEnum(YNEnum.class)
    private String commentStatus;
}
