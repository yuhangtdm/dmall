package com.dmall.component.web.handler;


import com.dmall.common.dto.BaseResult;

/**
 * @description: 公共Handle
 * @author: created by hang.yu on 2019/11/19 23:28
 */
public interface CommonHandler<ReqDTO, DO, ResDTO> {

    /**
     * 校验参数公共方法
     */
    BaseResult validate(ReqDTO dto);

    /**
     * 核心处理的方法
     */
    BaseResult handler(ReqDTO dto);

    /**
     * DTO转为DO
     */
    DO dtoConvertDo(ReqDTO dto, Class<DO> doClazz);

    /**
     * DO转换为DTO
     */
    ResDTO doConvertDto(DO doo, Class<ResDTO> doClazz);

}
