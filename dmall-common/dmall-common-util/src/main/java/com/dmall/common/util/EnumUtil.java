package com.dmall.common.util;

import com.dmall.common.enums.base.CodeEnum;
import com.dmall.common.enums.base.CodeDescDataEnum;
import com.dmall.common.enums.base.CodeDescEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description: 枚举工具类
 * @author: created by hang.yu on 2019/11/23 17:33
 */
public class EnumUtil {

    /**
     * 获取枚举对象
     */
    public static <E extends CodeDescEnum, CODE> E getCodeDescEnum(Class<E> enumClazz, CODE code) {
        if (enumClazz.isEnum()) {
            for (CodeDescEnum enumConstant : enumClazz.getEnumConstants()) {
                if (Objects.equals(enumConstant.getCode(), code)) {
                    return (E) enumConstant;
                }
            }
        }
        return null;
    }

    public static List<Object> getAllCode(Class<? extends CodeEnum> enumClazz) {
        List<Object> codeList = new ArrayList<>();
        if (enumClazz.isEnum()) {
            for (CodeEnum enumConstant : enumClazz.getEnumConstants()) {
                codeList.add(enumConstant.getCode());
            }
        }
        return codeList;
    }

    /**
     * 获取枚举描述
     */
    public static <CODE> String getDesc(Class<? extends CodeDescEnum> enumClazz, CODE code) {
        CodeDescEnum<CODE> codeDescEnum = getCodeDescEnum(enumClazz, code);
        return codeDescEnum == null ? null : codeDescEnum.getDesc();
    }

    /**
     * 获取枚举数据
     */
    public static <CODE, T> T getData(Class<? extends CodeDescEnum> enumClazz, CODE code) {
        CodeDescEnum<CODE> codeDescEnum = getCodeDescEnum(enumClazz, code);
        if (codeDescEnum == null) {
            return null;
        }
        if (codeDescEnum instanceof CodeDescDataEnum) {
            CodeDescDataEnum<T, CODE> keyValueDataEnum = (CodeDescDataEnum) codeDescEnum;
            return keyValueDataEnum.getData();
        }
        return null;
    }
}
