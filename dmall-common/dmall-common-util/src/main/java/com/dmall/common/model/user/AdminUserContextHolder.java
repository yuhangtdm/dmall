package com.dmall.common.model.user;

/**
 * @description: AdminUserContextHolder
 * @author: created by hang.yu on 2020/1/7 22:16
 */
public class AdminUserContextHolder {

    private AdminUserContextHolder() {
    }

    private static final InheritableThreadLocal<UserDTO> LOCAL = new InheritableThreadLocal();


    public static void set(UserDTO user) {
        LOCAL.set(user);
    }

    public static UserDTO get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
