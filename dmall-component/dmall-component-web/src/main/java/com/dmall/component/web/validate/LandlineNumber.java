package com.dmall.component.web.validate;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/23 17:56
 */
@Documented
@Constraint(validatedBy = LandlineNumberValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface LandlineNumber {

    String message() default "座机号不合法";

    Class[] groups() default {};

    Class[] payload() default {};
}
