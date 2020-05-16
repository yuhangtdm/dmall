package com.dmall.bms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.enums.OperationEnum;
import com.dmall.bms.generator.dataobject.UserPermissionDO;
import com.dmall.bms.generator.mapper.UserPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: UserPermissionSupport
 * @author: created by hang.yu on 2020/5/16 22:14
 */
@Component
public class UserPermissionSupport {

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    public void deleteByUserId(Long userId) {
        userPermissionMapper.delete(Wrappers.<UserPermissionDO>lambdaQuery().eq(UserPermissionDO::getUserId, userId));
    }

    public List<UserPermissionDO> listSubByUserId(Long userId) {
        return userPermissionMapper.selectList(Wrappers.<UserPermissionDO>lambdaQuery()
                .eq(UserPermissionDO::getUserId, userId)
                .eq(UserPermissionDO::getType, OperationEnum.SUB.getCode()));
    }
}
