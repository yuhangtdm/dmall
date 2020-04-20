package com.dmall.component.web.util;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.util.ResultUtil;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Set;

/**
 * @description: 校验实体属性的工具类
 * @author: created by hang.yu on 2019/11/23 17:47
 */
public class ValidateUtil {

    /**
     * 使用自定义方法校验
     */
    public static <T> BaseResult validate(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (CollectionUtils.isEmpty(violations)) {
            return ResultUtil.success();
        }
        Map<String, String> error = Maps.newLinkedHashMap();
        for (ConstraintViolation<T> constraintViolation : violations) {
            System.out.println(constraintViolation);
            error.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return ResultUtil.fail(BasicStatusEnum.BAD_REQUEST, error);
    }

}
