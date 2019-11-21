package com.dmall.common.model.handler;

import com.dmall.common.model.result.BaseResult;

/**
 * @description: 公共Handle
 * @author: created by yuhang on 2019/11/19 23:28
 */
public interface CommonHandler<DTO, DO> {

    /**
     * 校验参数公共方法
     */
    BaseResult validate(DTO dto);

    /**
     * 核心处理的方法
     */
    BaseResult handler(DTO dto);

    /**
     * DTO转为DO
     */
    DO dtoConvertDo(DTO dto, Class<DO> doClazz);

    /**
     * DO转换为DTO
     */
    DTO doConvertDto(DO doo, Class<DTO> doClazz);

}
