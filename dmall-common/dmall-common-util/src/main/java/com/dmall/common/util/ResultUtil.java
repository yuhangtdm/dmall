package com.dmall.common.util;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.enums.error.ErrorCodeEnum;
import com.dmall.common.model.exception.ComponentException;
import com.dmall.common.model.exception.BusinessException;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @description: 结果返回工具类
 * @author: created by hang.yu on 2019/11/7 22:48
 */
public class ResultUtil {

    public static BaseResult success() {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(Boolean.TRUE);
        baseResult.setCode(BasicStatusEnum.SUCCESS.getCode());
        baseResult.setMsg(BasicStatusEnum.SUCCESS.getMsg());
        return baseResult;
    }

    public static <T> BaseResult<T> success(T t) {
        Assert.notNull(t, "data不可为null");
        BaseResult<T> baseResult = new BaseResult();
        baseResult.setResult(Boolean.TRUE);
        baseResult.setCode(BasicStatusEnum.SUCCESS.getCode());
        baseResult.setData(t);
        baseResult.setMsg(BasicStatusEnum.SUCCESS.getMsg());
        if (t instanceof Collection) {
            Collection c = (Collection) t;
            baseResult.setCount(c.size());
        }
        return baseResult;
    }

    public static BaseResult success(ErrorCodeEnum errorCodeEnum) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(Boolean.TRUE);
        baseResult.setCode(errorCodeEnum.getCode());
        baseResult.setMsg(errorCodeEnum.getMsg());
        return baseResult;
    }

    public static <T> BaseResult<T> success(ErrorCodeEnum errorCodeEnum, T t) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setResult(Boolean.TRUE);
        baseResult.setCode(errorCodeEnum.getCode());
        baseResult.setData(t);
        baseResult.setMsg(errorCodeEnum.getMsg());
        return baseResult;
    }

    public static BaseResult fail() {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(Boolean.FALSE);
        baseResult.setCode(BasicStatusEnum.FAIL.getCode());
        baseResult.setMsg(BasicStatusEnum.FAIL.getMsg());
        return baseResult;
    }

    public static BaseResult fail(ErrorCodeEnum errorCodeEnum) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(Boolean.FALSE);
        baseResult.setMsg(errorCodeEnum.getMsg());
        baseResult.setCode(errorCodeEnum.getCode());
        return baseResult;
    }

    public static <T> BaseResult<T> fail(ErrorCodeEnum errorCodeEnum, T t) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setResult(Boolean.FALSE);
        baseResult.setMsg(errorCodeEnum.getMsg());
        baseResult.setCode(errorCodeEnum.getCode());
        baseResult.setData(t);
        return baseResult;
    }

    public static BaseResult fail(BusinessException businessException) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(Boolean.FALSE);
        baseResult.setCode(businessException.getCode());
        baseResult.setMsg(businessException.getMsg());
        return baseResult;
    }

    public static <T> BaseResult<T> fail(BusinessException businessException, T t) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setResult(Boolean.FALSE);
        baseResult.setCode(businessException.getCode());
        baseResult.setMsg(businessException.getMsg());
        baseResult.setData(t);
        return baseResult;
    }

    public static BaseResult fail(ComponentException componentException) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(componentException.getCode());
        baseResult.setMsg(componentException.getMsg());
        baseResult.setResult(Boolean.FALSE);
        return baseResult;
    }

    public static <T> BaseResult<T> fail(ComponentException componentException, T t) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setCode(componentException.getCode());
        baseResult.setMsg(componentException.getMsg());
        baseResult.setResult(Boolean.FALSE);
        baseResult.setData(t);
        return baseResult;
    }

    public static BaseResult fail(String code, String msg) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(Boolean.FALSE);
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        return baseResult;
    }
}
