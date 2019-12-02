package com.dmall.pms.api.dto.brand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.*;
import java.math.*;

/**
 * @description: 品牌分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PageBrandRequestDTO", description="品牌分页请求实体")
public class PageBrandRequestDTO  extends PageRequestDTO {

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "英文名称", position = 3)
    private String englishName;

    @ApiModelProperty(value = "首字母", position = 4)
    @NotBlank(message = "首字母不能为空")
    @Length(max = 1,min = 1,message = "首字母长度固定一位")
    private String firstLetter;

}
