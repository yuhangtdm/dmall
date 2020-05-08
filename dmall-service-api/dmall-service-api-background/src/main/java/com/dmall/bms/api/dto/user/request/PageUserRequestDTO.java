package com.dmall.bms.api.dto.user.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 后台用户分页请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageUserRequestDTO", description = "后台用户分页请求实体")
public class PageUserRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "手机号", position = 5)
    private String phone;

    @ApiModelProperty(value = "昵称", position = 6)
    private String nickName;

    @ApiModelProperty(value = "真实姓名", position = 7)
    private String realName;

}
