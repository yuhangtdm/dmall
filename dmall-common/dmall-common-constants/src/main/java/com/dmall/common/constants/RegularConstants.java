package com.dmall.common.constants;

/**
 * @description: 正则表达式常量
 * @author: created by hang.yu on 2019/11/23 20:00
 */
public interface RegularConstants {

    /**
     * 手机号正则表达式
     */
    String PHONE = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$";

    /**
     * 座机正则表达式
     */
    String LANDLINE = "/0\\d{2,3}-\\d{7,8}/";

    /**
     * 15位身份证号
     */
    String ID_NO = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";

    /**
     * 18位身份证号
     */
    String NORMARL_ID_NO = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    /**
     * 帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)
     */
    String RIGHT_ACCOUNT = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)：
     */
    String PASSWORD = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间)：
     */
    String STROUN_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$";

    /**
     * qq号
     */
    String QQ = "[1-9][0-9]{4,}";

    /**
     * 邮政编码
     */
    String POSTAL_CODE = "[1-9]\\d{5}(?!\\d)";
}
