package com.dmall.mms.api.dto.membercolletionsubject.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.membercolletionsubject.common.CommonMemberColletionSubjectRequestDTO;

/**
 * @description: 新增会员收藏专题表 请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveMemberColletionSubjectRequestDTO", description="新增会员收藏专题表 请求实体")
public class SaveMemberColletionSubjectRequestDTO extends CommonMemberColletionSubjectRequestDTO {

}
