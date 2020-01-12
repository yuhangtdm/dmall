package com.dmall.bms.api.dto.permission.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.permission.common.CommonPermissionRequestDTO;

/**
 * @description: 新增资源请求实体
 * @author: created by hang.yu on 2020-01-11 18:47:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SavePermissionRequestDTO", description = "新增资源请求实体")
public class SavePermissionRequestDTO extends CommonPermissionRequestDTO {

        }
