package com.dmall.mms.api.dto.member.request;

import com.dmall.mms.api.dto.member.common.CommonMemberRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 新增会员请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMemberRequestDTO", description = "新增会员请求实体")
public class SaveMemberRequestDTO extends CommonMemberRequestDTO {

}
