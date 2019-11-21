package com.dmall.component.web.handler;

import cn.hutool.core.map.MapUtil;
import com.dmall.common.constants.component.web.WebConstants;
import com.dmall.common.enums.base.BasicStatusEnum;
import com.dmall.common.model.exception.ComponentException;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.exception.BusinessException;
import com.dmall.component.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: 全局异常处理器
 * @author: created by yuhang on 2019/11/7 22:23
 */
@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public String businessHandle(BusinessException ex, HttpServletRequest request) {
        log.error("enter the BusinessException Handler,",ex);
        return getCustomException(request, ResultUtil.fail(ex));
    }

    /**
     * 自定义组件异常
     */
    @ExceptionHandler(ComponentException.class)
    public String componentHandle(ComponentException ex, HttpServletRequest request) {
        log.error("enter the ComponentException Handler,",ex);
        return getCustomException(request, ResultUtil.fail(ex));
    }

    /**
     * 请求路径不正确异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String NoHandlerFoundHandle(HttpServletRequest request) {
        log.error("enter the NoHandlerFoundException Handler");
        BaseResult fail = ResultUtil.fail(BasicStatusEnum.NOT_FOUND_REQUEST);
        request.setAttribute(WebConstants.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());
        request.setAttribute(WebConstants.DATA, fail);
        return WebConstants.FORWARD_ERROR;
    }

    /**
     * 校验参数异常,json参数
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String bind(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("enter the MethodArgumentNotValidException Handler");
        return paramHandle(e.getBindingResult().getFieldErrors(), null, request);
    }

    /**
     * 校验参数异常,普通表单
     */
    @ExceptionHandler(BindException.class)
    public String bind(BindException e, HttpServletRequest request) {
        log.error("enter the BindException Handler");
        return paramHandle(e.getBindingResult().getFieldErrors(), null, request);
    }

    /**
     * 校验参数异常,方法参数
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public String bind(ConstraintViolationException e, HttpServletRequest request) {
        log.error("enter the ConstraintViolationException Handler");
        Map<String,String> map = new LinkedHashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            map.put(constraintViolation.getMessageTemplate(),constraintViolation.getMessage());
        }
        return paramHandle(null, map, request);
    }

    /**
     * 处理未知异常
     */
    @ExceptionHandler(Exception.class)
    public String exception(Exception ex, HttpServletRequest request) {
        log.error("enter the Unknown exception Handler,",ex);
        return getCustomException(request, ResultUtil.fail());
    }

    /**
     * 处理参数异常的公共逻辑
     */
    private String paramHandle(List<FieldError> fieldErrors, Map<String,String> error, HttpServletRequest request) {
        Map<String,String> map = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(fieldErrors)) {
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
        }
        return getCustomException(request, ResultUtil.fail(BasicStatusEnum.BAD_REQUEST, MapUtil.isEmpty(error) ? map : error));
    }

    /**
     * 处理异常的公共逻辑
     */
    private String getCustomException(HttpServletRequest request, BaseResult fail) {
        request.setAttribute(WebConstants.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        request.setAttribute(WebConstants.DATA, fail);
        return WebConstants.FORWARD_ERROR;
    }

}
