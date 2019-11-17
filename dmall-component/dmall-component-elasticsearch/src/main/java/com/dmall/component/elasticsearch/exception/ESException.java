package com.dmall.component.elasticsearch.exception;

import com.dmall.common.enums.base.ErrorCodeEnum;
import com.dmall.common.model.exception.ComponentException;
import lombok.Getter;

/**
 * @description: elsticSearch异常类
 * @author: created by yuhang on 2019/11/6 23:40
 */
@Getter
public class ESException extends ComponentException {

    public ESException(){
        super(ESErrorEnum.BASIC_ERROR.getCode(), ESErrorEnum.BASIC_ERROR.getMsg());
    }

    public ESException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    public ESException(String code, String msg){
        super(code, msg);
    }


}
