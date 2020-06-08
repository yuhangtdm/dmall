package com.dmall.common.model.admin;

/**
 * @description: AdminUserContextHolder
 * @author: created by hang.yu on 2020/1/7 22:16
 */
public class AdminUserContextHolder {

    private AdminUserContextHolder() {}

    private static final InheritableThreadLocal<AdminUserDTO> LOCAL = new InheritableThreadLocal();

    public static void set(AdminUserDTO user) {
        LOCAL.set(user);
    }

    public static AdminUserDTO get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
