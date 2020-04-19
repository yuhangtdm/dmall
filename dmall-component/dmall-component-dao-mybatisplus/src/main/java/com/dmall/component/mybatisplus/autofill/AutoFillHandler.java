package com.dmall.component.mybatisplus.autofill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dmall.common.constants.Constants;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.component.mybatisplus.DMallMybatisPlusProperties;
import com.dmall.component.mybatisplus.generator.MybatisPlusConstants;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @description: 时间的自动填充
 * @author: created by hang.yu on 2019/11/21 21:52
 */
public class AutoFillHandler implements MetaObjectHandler {

    private final DMallMybatisPlusProperties dMallMybatisPlusProperties;

    public AutoFillHandler(DMallMybatisPlusProperties dMallMybatisPlusProperties) {
        this.dMallMybatisPlusProperties = dMallMybatisPlusProperties;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        Object createTimeType = getFieldValByName(MybatisPlusConstants.GMT_CREATED, metaObject);
        if (createTimeType == null) {
            setFieldValByName(MybatisPlusConstants.GMT_CREATED, new Date(), metaObject);
        }
        // 更新时间
        Object UpdateTimeType = getFieldValByName(MybatisPlusConstants.GMT_MODIFIED, metaObject);
        if (UpdateTimeType == null) {
            setFieldValByName(MybatisPlusConstants.GMT_MODIFIED, new Date(), metaObject);
        }
        // 状态 默认为有效
        Object IsDeletedType = getFieldValByName(MybatisPlusConstants.IS_DELETED, metaObject);
        if (IsDeletedType == null) {
            setFieldValByName(MybatisPlusConstants.IS_DELETED, Constants.N, metaObject);
        }
        // 后台系统创建人和更新人
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        if (adminUserDTO != null) {
            setFieldValByName(MybatisPlusConstants.CREATOR, adminUserDTO.getId(), metaObject);
            setFieldValByName(MybatisPlusConstants.MODIFIER, adminUserDTO.getId(), metaObject);
        }
        // 前台商城创建人和更新人
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (adminUserDTO != null) {
            setFieldValByName(MybatisPlusConstants.CREATOR, portalMemberDTO.getId(), metaObject);
            setFieldValByName(MybatisPlusConstants.MODIFIER, portalMemberDTO.getId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置更新时间
        setFieldValByName(MybatisPlusConstants.GMT_MODIFIED, new Date(), metaObject);
        // 后台系统设置修改人
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        if (adminUserDTO != null) {
            setFieldValByName(MybatisPlusConstants.MODIFIER, adminUserDTO.getId(), metaObject);
        }
        // 前台商城创建人和更新人
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (adminUserDTO != null) {
            setFieldValByName(MybatisPlusConstants.MODIFIER, portalMemberDTO.getId(), metaObject);
        }
    }
}
