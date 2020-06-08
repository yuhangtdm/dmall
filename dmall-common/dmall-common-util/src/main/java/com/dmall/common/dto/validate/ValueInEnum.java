package com.dmall.common.dto.validate;

import com.dmall.common.enums.base.CodeEnum;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @description: ValueInEnum
 * @author: created by hang.yu on 2019/11/23 17:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = EnumValidator.class)
public @interface ValueInEnum {

    Class<? extends CodeEnum> value();

    String message() default "入参值不在枚举中";

    Class<?>[] groups() default {};

    Class[] payload() default {};

}