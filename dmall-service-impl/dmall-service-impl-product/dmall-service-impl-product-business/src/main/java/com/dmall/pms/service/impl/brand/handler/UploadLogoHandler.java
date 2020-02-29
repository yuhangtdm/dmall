package com.dmall.pms.service.impl.brand.handler;

import com.dmall.common.dto.UploadResult;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.component.file.qiniu.QiNiuConstants;
import com.dmall.component.file.qiniu.QiNiuFileManager;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.api.enums.BrandErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: 上传品牌logo处理器
 * @author: created by hang.yu on 2019/12/17 11:30
 */
@Slf4j
@Component
public class UploadLogoHandler extends AbstractCommonHandler<MultipartFile, BrandDO, UploadResult> {

    @Autowired
    private QiNiuFileManager qiNiuFileManager;

    @Override
    public BaseResult<UploadResult> processor(MultipartFile file) {
        try {
            UploadResult uploadResult = qiNiuFileManager.upload(file, QiNiuConstants.BRAND);
            return ResultUtil.success(uploadResult);
        } catch (IOException e) {
            log.error("upload logo failed", e);
            return ResultUtil.success(BrandErrorEnum.UPLOAD_LOGO_ERROR);
        }
    }

}
