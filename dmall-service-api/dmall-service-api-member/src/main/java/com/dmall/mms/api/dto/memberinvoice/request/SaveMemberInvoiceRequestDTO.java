package com.dmall.mms.api.dto.memberinvoice.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberinvoice.common.CommonMemberInvoiceRequestDTO;

/**
 * @description: 新增会员发票请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveMemberInvoiceRequestDTO", description="新增会员发票请求实体")
public class SaveMemberInvoiceRequestDTO extends CommonMemberInvoiceRequestDTO {

}
