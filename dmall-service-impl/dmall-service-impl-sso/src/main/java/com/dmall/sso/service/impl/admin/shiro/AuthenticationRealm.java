package com.dmall.sso.service.impl.admin.shiro;

import com.dmall.common.constants.Constants;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.BeanUtil;
import com.dmall.sso.service.impl.admin.dataobject.UserDO;
import com.dmall.sso.service.impl.admin.support.UserSupport;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: AuthenticationRealm
 * @author: created by hang.yu on 2020/1/11 16:42
 */
public class AuthenticationRealm extends AuthorizingRealm {

    @Autowired
    private UserSupport userSupport;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        UserDO userDO = userSupport.getByUserName(userName);
        if (userDO == null) {
            throw new UnknownAccountException();
        }
        if (YNEnum.Y.getCode().equals(userDO.getIsDeleted())) {
            throw new LockedAccountException();
        }
        AdminUserDTO adminUserDTO = BeanUtil.copyProperties(userDO, AdminUserDTO.class);
        adminUserDTO.setSource(Constants.ADMIN_USER);
        // 密码加密和比较交给shiro
        return new SimpleAuthenticationInfo(adminUserDTO, userDO.getPassword(),
                    ByteSource.Util.bytes(userDO.getUserName()),
                    getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
