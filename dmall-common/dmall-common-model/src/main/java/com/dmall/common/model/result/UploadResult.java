package com.dmall.common.model.result;

import lombok.Data;

/**
 * @description: 上传文件的返回结果
 * @author: created by hang.yu on 2019/12/17 11:32
 */
@Data
public class UploadResult {

    /**
     * 上传文件后的访问外链
     */
    private String src;

    /**
     * 上传文件的key
     */
    private String key;

    /**
     * 上传文件后生成的hash
     */
    private String hash;
}
