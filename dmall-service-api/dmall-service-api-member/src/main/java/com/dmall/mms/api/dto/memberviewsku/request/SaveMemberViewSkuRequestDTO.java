package com.dmall.mms.api.dto.memberviewsku.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuRequestDTO;

/**
 * @description: 新增会员浏览历史记录请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveMemberViewSkuRequestDTO", description="新增会员浏览历史记录请求实体")
public class SaveMemberViewSkuRequestDTO extends CommonMemberViewSkuRequestDTO {

}
