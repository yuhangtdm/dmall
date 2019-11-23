package com.dmall.mms.api.dto.member.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 会员响应实体
 * @author: created by hang.yu on 2019/10/15 22:27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MemberResponseDTO implements Serializable {

    private static final long serialVersionUID = -3107714788940587671L;

    /**
     * id
     */
    private Long id;

    /**
     * 会员用户名
     */
    private String name;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别 1-男;2-女;3-保密
     */
    private Integer gender;

}
