package com.dmall.component.mybatisplus.generator;

import com.dmall.common.enums.base.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: Handler操作枚举
 * @author: created by hang.yu on 2020/4/19 18:08
 */
@Getter
@AllArgsConstructor
public enum HandlerEnum implements CodeEnum<String> {

    /**
     * Save
     */
    SAVE("Save"),

    /**
     * Delete
     */
    DELETE("Delete"),

    /**
     * Update
     */
    UPDATE("Update"),

    /**
     * Get
     */
    GET("Get"),

    /**
     * List
     */
    LIST("List"),

    /**
     * Page
     */
    PAGE("Page");

    private final String code;
}
