package com.dmall.component.file.qiniu.exception;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: QiNiuErrorEnum
 * @author: created by hang.yu on 2019/12/17 10:41
 */
@Getter
@AllArgsConstructor
public enum QiNiuErrorEnum implements ErrorCodeEnum {

    QI_NIU_ERROR_ENUM("qiniu_001" , "七牛云服务异常" ),
    QI_NIU_CONFIG_ERROR("qiniu_002" , "七牛云配置异常" ),
    ;

    private String code;

    private String msg;

}
