package com.dmall.common.model.handler;

/**
 * @description: 抽象Handler
 * @author: created by yuhang on 2019/11/19 23:33
 */
public abstract class AbstractCommonHandler<DTO,DO> implements CommonHandler<DTO,DO> {

    @Override
    public DO dtoConvertDo(DTO dto,Class<DO> doClazz) {
        DO result = BeanUtil.copyProperties(dto, doClazz);
        customerConvertDo(result);
        return result;
    }

    @Override
    public DTO doConvertDto(DO doo, Class<DTO> doClazz) {
        DTO result = BeanUtil.copyProperties(doo, doClazz);
        customerConvertDto(result);
        return result;
    }

    /**
     * 子类可以重写,自定义转换do
     */
    protected  void  customerConvertDo(DO result){

    }

    /**
     * 子类可以重写,自定义转换dto
     */
    protected void  customerConvertDto(DTO result){

    }

}
