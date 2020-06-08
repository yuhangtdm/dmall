package com.dmall.common.model.portal;

/**
 * @description: PortalMemberContextHolder
 * @author: created by hang.yu on 2020/1/7 22:16
 */
public class PortalMemberContextHolder {

    private PortalMemberContextHolder() {}

    private static final InheritableThreadLocal<PortalMemberDTO> LOCAL = new InheritableThreadLocal();

    public static void set(PortalMemberDTO user) {
        LOCAL.set(user);
    }

    public static PortalMemberDTO get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
