package com.dmall.component.mybatisplus.autofill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dmall.common.constants.Constants;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.component.mybatisplus.DMallMybatisPlusProperties;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @description: 时间的自动填充
 * @author: created by hang.yu on 2019/11/21 21:52
 */
public class AutoFillHandler implements MetaObjectHandler {

    private DMallMybatisPlusProperties dMallMybatisPlusProperties;

    public AutoFillHandler(DMallMybatisPlusProperties dMallMybatisPlusProperties) {
        this.dMallMybatisPlusProperties = dMallMybatisPlusProperties;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTimeType = getFieldValByName(dMallMybatisPlusProperties.getCreateTimeColumn(), metaObject);
        if (createTimeType == null) {
            setFieldValByName(dMallMybatisPlusProperties.getCreateTimeColumn(), new Date(), metaObject);
        }
        Object UpdateTimeType = getFieldValByName(dMallMybatisPlusProperties.getUpdateTimeColumn(), metaObject);
        if (UpdateTimeType == null) {
            setFieldValByName(dMallMybatisPlusProperties.getUpdateTimeColumn(), new Date(), metaObject);
        }

        Object IsDeletedType = getFieldValByName(dMallMybatisPlusProperties.getIsDeletedColumn(), metaObject);
        if (IsDeletedType == null) {
            setFieldValByName(dMallMybatisPlusProperties.getIsDeletedColumn(), Constants.N, metaObject);
        }

        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        if (adminUserDTO != null) {
            setFieldValByName(dMallMybatisPlusProperties.getCreator(), adminUserDTO.getId(), metaObject);
            setFieldValByName(dMallMybatisPlusProperties.getModifier(), adminUserDTO.getId(), metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(dMallMybatisPlusProperties.getUpdateTimeColumn(), new Date(), metaObject);
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        if (adminUserDTO != null) {
            setFieldValByName(dMallMybatisPlusProperties.getModifier(), adminUserDTO.getId(), metaObject);
        }
    }
}
