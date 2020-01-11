package com.dmall.component.file.qiniu;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.dmall.common.dto.UploadResult;
import com.dmall.component.file.qiniu.exception.QiNiuErrorEnum;
import com.dmall.component.file.qiniu.exception.QiNiuException;
import com.google.common.collect.Lists;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description: 七牛云管理类
 * @author: created by hang.yu on 2019/12/17 10:20
 */
@Slf4j
public class QiNiuFileManager {

    private static final String THUMB_BASE = "imageView2/2/w/%s/h/%s";
    private QiNiuProperties qiNiuProperties;
    private Region defaultRegion = Region.huadong();


    public QiNiuFileManager(QiNiuProperties qiNiuProperties) {
        this.qiNiuProperties = qiNiuProperties;
    }

    /**
     * 获取简单上传的token
     */
    public String getUpToken() {
        Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());
        return auth.uploadToken(qiNiuProperties.getBucket());
    }

    /**
     * 获取覆盖上传的token
     */
    public String getUpToken(String key) {
        if (StrUtil.isBlank(key)) {
            return getUpToken();
        }
        Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());
        return auth.uploadToken(qiNiuProperties.getBucket(), key);
    }

    /**
     * 上传文件
     */
    public List<UploadResult> upload(MultipartFile[] files, String catalog) throws IOException {
        List<UploadResult> uploadResults = Lists.newArrayList();
        for (MultipartFile file : files) {
            uploadResults.add(upload(file, catalog));
        }
        return uploadResults;
    }

    /**
     * 上传文件
     */
    public UploadResult upload(MultipartFile file, String catalog) throws IOException {
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        DefaultPutRet upload = this.upload(file.getInputStream(), catalog, fileType);
        UploadResult uploadResult = new UploadResult();
        uploadResult.setKey(upload.key);
        uploadResult.setHash(upload.hash);
        uploadResult.setUrl(this.get(upload.key));
        return uploadResult;
    }

    /**
     * 文件上传
     */
    public DefaultPutRet upload(InputStream inputStream, String catalog, String fileType) {
        return upload(inputStream, getKey(catalog, fileType));
    }

    /**
     * 文件上传
     */
    public DefaultPutRet upload(InputStream inputStream, String key) {
        Configuration cfg = new Configuration(defaultRegion);
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String upToken = getUpToken(key);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet defaultPutRet = JSONObject.parseObject(response.bodyString(), DefaultPutRet.class);
            log.info("upload file success,key:{},hash:{}", defaultPutRet.key, defaultPutRet.hash);
            return defaultPutRet;
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                log.error("upload file failed,{},{}", r.toString(), r.bodyString());
            } catch (QiniuException ex2) {
                // ignore
            }
            throw new QiNiuException(QiNiuErrorEnum.QI_NIU_ERROR_ENUM);
        }
    }

    /**
     * 删除文件
     */
    public void delete(String key) {
        Configuration cfg = new Configuration(defaultRegion);

        Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(qiNiuProperties.getBucket(), key);
            log.info("delete file success,key:{}", key);
        } catch (QiniuException ex) {
            log.error("delete file failed，code:{},msg:{}", ex.code(), ex.response.toString());
            throw new QiNiuException(QiNiuErrorEnum.QI_NIU_ERROR_ENUM);
        }
    }

    /**
     * 获取文件
     */
    public String get(String key) {
        return StrUtil.format("{}/{}?{}", qiNiuProperties.getDomain(), key, System.currentTimeMillis());
    }

    /**
     * 获取指定大小的文件
     */
    public String getModel(String key, int size) {
        return StrUtil.format("{}/{}?v={}&{}", qiNiuProperties.getDomain(), key, System.currentTimeMillis(), getSize(size));
    }

    /**
     * 获取文件的唯一key
     *
     * @param catalog  目录
     * @param fileType 文件类型
     */
    public String getKey(String catalog, String fileType) {
        String key = StrUtil.format("{}.{}", IdUtil.simpleUUID(), fileType);
        return StrUtil.format("{}/{}/{}", qiNiuProperties.getNamespace(), catalog, key);
    }

    private String getSize(int size) {
        return String.format(THUMB_BASE, size, size);
    }
}
