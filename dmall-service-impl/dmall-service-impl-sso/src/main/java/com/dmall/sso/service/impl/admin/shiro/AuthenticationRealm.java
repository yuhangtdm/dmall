package com.dmall.sso.service.impl.admin.shiro;

import com.dmall.common.constants.Constants;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.RequestUtil;
import com.dmall.sso.service.impl.admin.dataobject.UserDO;
import com.dmall.sso.service.impl.admin.dataobject.UserLoginLogDO;
import com.dmall.sso.service.impl.admin.mapper.UserLoginLogMapper;
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

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String phone = (String) authenticationToken.getPrincipal();
        UserDO userDO = userSupport.getByPhone(phone);
        if (userDO == null) {
            throw new UnknownAccountException();
        }
        if (YNEnum.N.getCode().equals(userDO.getStatus())) {
            throw new LockedAccountException();
        }
        // 插入登录记录
        insertLoginLog(userDO);
        AdminUserDTO adminUserDTO = BeanUtil.copyProperties(userDO, AdminUserDTO.class);
        adminUserDTO.setSource(Constants.ADMIN_USER);
        // 密码加密和比较交给shiro
        return new SimpleAuthenticationInfo(adminUserDTO, userDO.getPassword(),
                ByteSource.Util.bytes(userDO.getPhone()),
                getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    private void insertLoginLog(UserDO userDO) {
        UserLoginLogDO userLoginLogDO = new UserLoginLogDO();
        userLoginLogDO.setUserId(userDO.getId());
        userLoginLogDO.setIp(RequestUtil.getIpAddress(RequestUtil.getRequest()));
        userLoginLogMapper.insert(userLoginLogDO);
    }

}
