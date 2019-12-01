package com.dmall.mms.api.dto.memberloginlog.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberloginlog.common.CommonMemberLoginLogRequestDTO;

/**
 * @description: 新增会员登录记录请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveMemberLoginLogRequestDTO", description="新增会员登录记录请求实体")
public class SaveMemberLoginLogRequestDTO extends CommonMemberLoginLogRequestDTO {

}
