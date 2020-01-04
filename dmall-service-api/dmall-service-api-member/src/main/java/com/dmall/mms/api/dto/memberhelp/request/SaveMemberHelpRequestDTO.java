package com.dmall.mms.api.dto.memberhelp.request;

import com.dmall.mms.api.dto.memberhelp.common.CommonMemberHelpRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 新增会员-帮助关系表 帮助对会员有用请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMemberHelpRequestDTO" , description = "新增会员-帮助关系表 帮助对会员有用请求实体" )
public class SaveMemberHelpRequestDTO extends CommonMemberHelpRequestDTO {

}
