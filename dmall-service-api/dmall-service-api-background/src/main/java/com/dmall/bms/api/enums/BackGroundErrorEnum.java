package com.dmall.bms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台错误枚举 1000开头
 * @author: created by hang.yu on 2020/4/21 22:13
 */
@Getter
@AllArgsConstructor
public enum BackGroundErrorEnum implements ErrorCodeEnum {

    /**
     * 该商家发货仓库不存在
     */
    DELIVER_WAREHOUSE_NOT_EXIST("1000", "该商家发货仓库不存在"),

    /**
     * 菜单上级不存在
     */
    PARENT_NOT_EXIST("1011", "菜单上级不存在"),

    /**
     * 菜单上级不是目录
     */
    PARENT_NOT_CATALOG("1012", "菜单上级不是目录"),

    /**
     * 菜单地址不能为空
     */
    URL_BLANK("1013", "菜单地址不能为空"),

    /**
     * 父级id不能为空
     */
    PARENT_NOT_NULL("1014", "父级id不能为空"),

    /**
     * 该菜单不存在
     */
    MENU_NOT_EXIST("1015", "该菜单不存在"),

    /**
     * 该权限code已存在
     */
    CODE_EXIST("1020", "该权限code已存在"),

    /**
     * 该接口地址和请求方式已存在
     */
    URI_METHOD_EXIST("1021", "该接口地址和请求方式已存在"),

    /**
     * 该服务不存在
     */
    SERVICE_NOT_EXIST("1022", "该服务不存在"),

    /**
     * 该权限不存在
     */
    PERMISSION_NOT_EXIST("1023", "该权限不存在"),

    /**
     * 该角色名称已存在
     */
    NAME_EXIST("1030","该角色名称已存在"),

    /**
     * 该后台角色不存在
     */
    ROLE_NOT_EXIST("1031","该后台角色不存在"),

    /**
     * 该用户名称已存在
     */
    USER_NAME_EXIST("1040","该用户名称已存在"),

    /**
     * 该用户不存在
     */
    USER_NOT_EXIST("1041","该用户不存在"),

    /**
     * 上传头像失败
     */
    UPLOAD_PIC_ERROR("1042","上传头像失败"),

    /**
     * 角色id不存在
     */
    ROLE_ID_NOT_EXIST("1043","角色id不存在"),

    /**
     * 权限id不存在
     */
    PERMISSION_ID_NOT_EXIST("1044","权限id不存在"),

    /**
     * 不可修改他人的信息
     */
    NO_AUTH_UPDATE("1045", "不可修改他人的信息"),

    /**
     * 原密码不正确
     */
    PASSWORD_ERROR("1046","原密码不正确"),

    /**
     * 默认是否打开不能为空
     */
    OPEN_NOT_EMPTY("1047", " 默认是否打开不能为空"),

    ;
    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;
}
