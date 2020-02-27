package com.dmall.mms.api.dto.advice.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.advice.common.CommonAdviceRequestDTO;

/**
 * @description: 新增会员意见表 请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveAdviceRequestDTO", description = "新增会员意见表 请求实体")
public class SaveAdviceRequestDTO extends CommonAdviceRequestDTO {

}
