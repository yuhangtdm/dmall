package com.dmall.component.rbac.shiro.util;

import com.dmall.common.constants.Constants;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @description: 密码工具类
 * @author: created by hang.yu on 2020/1/11 17:12
 */
public class PasswordUtil {

    /**
     * 加密
     */
    public static String getPassword(String userName, String password){
        return new SimpleHash(Constants.MD5,password, ByteSource.Util.bytes(userName),Constants.ENCRYPT_TIME).toString();
    }

    public static void main(String[] args) {
        System.out.println(getPassword("ls","123456"));
    }
}
