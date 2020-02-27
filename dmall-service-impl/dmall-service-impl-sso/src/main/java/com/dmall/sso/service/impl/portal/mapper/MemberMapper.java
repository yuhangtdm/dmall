package com.dmall.sso.service.impl.portal.mapper;

import com.dmall.sso.service.impl.portal.dataobject.MemberDO;
import org.springframework.data.repository.query.Param;

/**
 * @description: 会员表
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
public interface MemberMapper {

    MemberDO login(@Param("memberName") String memberName);
}
