package com.dmall.component.web.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.BeanUtil;

/**
 * @description: 抽象Handler
 * @author: created by hang.yu on 2019/11/19 23:33
 */
public abstract class AbstractCommonHandler<ReqDTO, DO, ResDTO> implements CommonHandler<ReqDTO, DO, ResDTO> {

    /**
     * 核心处理的方法
     */
    @Override
    public BaseResult handler(ReqDTO dto) {
        BaseResult validate = validate(dto);
        if (validate == null || validate.getResult()) {
            return processor(dto);
        }
        return validate;
    }

    /**
     * DTO转为DO
     */
    @Override
    public DO dtoConvertDo(ReqDTO dto, Class<DO> doClazz) {
        if (dto == null) {
            return null;
        }
        DO result = BeanUtil.copyProperties(dto, doClazz);
        customerConvertDo(result, dto);
        return result;
    }

    /**
     * DO转为DTO
     */
    @Override
    public ResDTO doConvertDto(DO doo, Class<ResDTO> doClazz) {
        if (doo == null) {
            return null;
        }
        ResDTO result = BeanUtil.copyProperties(doo, doClazz);
        customerConvertDto(result, doo);
        return result;
    }

    @Override
    public BaseResult validate(ReqDTO reqDTO) {
        return null;
    }

    /**
     * 子类实现，处理业务
     */
    public abstract BaseResult processor(ReqDTO dto);

    /**
     * 子类可以重写,自定义转换do
     */
    protected void customerConvertDo(DO result, ReqDTO dto) {}

    /**
     * 子类可以重写,自定义转换dto
     */
    protected void customerConvertDto(ResDTO result, DO doo) {}

}
