package com.dmall.component.web.validate;

import com.dmall.common.constants.RegularConstants;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 手机号校验类
 * @author: created by hang.yu on 2019/11/23 17:57
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext context) {
        if (phoneField == null) {
            return false;
        }
        return phoneField.matches(RegularConstants.PHONE) && phoneField.length() > 8 && phoneField.length() < 14;
    }
}