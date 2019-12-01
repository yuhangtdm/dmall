package com.dmall.mms.api.dto.memberhelp.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberhelp.common.CommonMemberHelpRequestDTO;

/**
 * @description: 新增会员-帮助关系表 帮助对会员有用请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveMemberHelpRequestDTO", description="新增会员-帮助关系表 帮助对会员有用请求实体")
public class SaveMemberHelpRequestDTO extends CommonMemberHelpRequestDTO {

}
