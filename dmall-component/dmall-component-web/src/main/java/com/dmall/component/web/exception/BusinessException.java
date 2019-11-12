package com.dmall.component.web.exception;

import com.dmall.common.enums.base.BasicStatusEnum;
import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.Data;

/**
 * @description: 自定义业务异常
 * @author: created by yuhang on 2019/11/7 22:25
 */
@Data
public class BusinessException extends RuntimeException  {

    private String code;

    private String msg;

    public BusinessException(){
        super(BasicStatusEnum.FAIL.getMsg());
        this.code = BasicStatusEnum.FAIL.getCode();
        this.msg = BasicStatusEnum.FAIL.getMsg();
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable cause){
        super(msg,cause);
        this.msg = msg;
    }

}
