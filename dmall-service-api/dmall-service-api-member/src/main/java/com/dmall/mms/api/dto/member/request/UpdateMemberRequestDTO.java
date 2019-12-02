package com.dmall.mms.api.dto.member.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.member.common.CommonMemberRequestDTO;

/**
 * @description: 修改会员请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateMemberRequestDTO", description="修改会员请求实体")
public class UpdateMemberRequestDTO extends CommonMemberRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
