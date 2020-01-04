package com.dmall.mms.api.dto.membercolletionsubject.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 会员收藏专题表 列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListMemberColletionSubjectRequestDTO", description = "会员收藏专题表 列表请求实体")
public class ListMemberColletionSubjectRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


}
