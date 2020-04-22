package com.dmall.pms.api.dto.sku.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @description: 上传文件请求实体
 * @author: created by hang.yu on 2019/12/17 12:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadRequestDTO implements Serializable {

    private static final long serialVersionUID = 939731237269407463L;

    @ApiModelProperty(value = "id", required = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "files", required = true, position = 2)
    private MultipartFile[] files;
}
