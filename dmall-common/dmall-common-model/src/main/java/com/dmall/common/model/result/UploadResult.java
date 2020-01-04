package com.dmall.common.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 上传文件的返回结果
 * @author: created by hang.yu on 2019/12/17 11:32
 */
@Data
@ApiModel(value = "UploadResult", description = "上传文件响应实体")
public class UploadResult {

    /**
     * 上传文件后的访问外链
     */
    @ApiModelProperty(value = "上传文件后的访问外链", position = 1)
    private String url;

    /**
     * 上传文件的key
     */
    @ApiModelProperty(value = "上传文件的key", position = 1)
    private String key;

    /**
     * 上传文件后生成的hash
     */
    @ApiModelProperty(value = "上传文件后生成的hash", position = 1)
    private String hash;
}
