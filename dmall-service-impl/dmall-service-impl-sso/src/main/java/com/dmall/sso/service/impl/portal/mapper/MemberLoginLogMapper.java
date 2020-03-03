package com.dmall.sso.service.impl.portal.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmall.sso.service.impl.portal.dataobject.MemberLoginLogDO;

/**
 * @description: 会员登录记录表
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@DS("portal")
public interface MemberLoginLogMapper extends BaseMapper<MemberLoginLogDO> {

}
