package com.dmall.mms.service.validate;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.mms.api.enums.InvoiceHeaderEnum;
import com.dmall.mms.api.enums.MmsErrorEnum;

/**
 * @description: 发票校验类
 * @author: created by hang.yu on 2020/4/23 22:03
 */
public class MemberInvoiceValidate {

    public static BaseResult validate(Integer invoiceHeader, String personalName, String companyName,
        String customerTaxNumber) {
        if (InvoiceHeaderEnum.PERSONAL.getCode().equals(invoiceHeader)) {
            if (StrUtil.isBlank(personalName)) {
                return ResultUtil.fail(MmsErrorEnum.PERSONAL_NAME_EMPTY);
            }
        } else {
            if (StrUtil.isBlank(companyName)) {
                return ResultUtil.fail(MmsErrorEnum.COMPANY_NAME_EMPTY);
            }
            if (StrUtil.isBlank(customerTaxNumber)) {
                return ResultUtil.fail(MmsErrorEnum.CUSTOMER_TAX_NUMBER_EMPTY);
            }
        }
        return ResultUtil.success();
    }
}
