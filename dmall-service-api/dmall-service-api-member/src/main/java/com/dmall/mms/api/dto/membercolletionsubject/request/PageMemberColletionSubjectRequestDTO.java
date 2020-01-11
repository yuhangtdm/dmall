package com.dmall.mms.api.dto.membercolletionsubject.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 会员收藏专题表 分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageMemberColletionSubjectRequestDTO", description = "会员收藏专题表 分页请求实体")
public class PageMemberColletionSubjectRequestDTO extends PageRequestDTO {


}
