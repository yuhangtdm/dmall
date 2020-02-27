package com.dmall.mms.api.dto.membersafe.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.membersafe.common.CommonMemberSafeRequestDTO;

/**
 * @description: 新增账户安全请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMemberSafeRequestDTO", description = "新增账户安全请求实体")
public class SaveMemberSafeRequestDTO extends CommonMemberSafeRequestDTO {

}
