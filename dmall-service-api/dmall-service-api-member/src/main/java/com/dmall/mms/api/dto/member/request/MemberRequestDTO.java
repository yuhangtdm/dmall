package com.dmall.mms.api.dto.member.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description: 会员请求实体
 * @author: created by yuhang on 2019/10/15 22:26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MemberRequestDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 会员用户名
     */
    private String name;

}
