package com.dmall.component.elasticsearch.entity;

import com.dmall.common.dto.validate.PhoneNumberValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description: 标记es的id列
 * @author: created by hang.yu on 2020/3/5 22:15
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface EsId {
}
