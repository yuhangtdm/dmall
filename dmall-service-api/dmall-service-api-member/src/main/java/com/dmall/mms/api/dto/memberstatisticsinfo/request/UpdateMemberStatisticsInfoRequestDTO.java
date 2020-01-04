package com.dmall.mms.api.dto.memberstatisticsinfo.request;

import com.dmall.mms.api.dto.memberstatisticsinfo.common.CommonMemberStatisticsInfoRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 修改会员统计信息请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateMemberStatisticsInfoRequestDTO" , description = "修改会员统计信息请求实体" )
public class UpdateMemberStatisticsInfoRequestDTO extends CommonMemberStatisticsInfoRequestDTO {

    @ApiModelProperty(value = "主键" , required = true, position = 0)
    private Long id;

}
