package com.dmall.oms.service.impl.order.handler.aftersale;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.UploadResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.file.qiniu.QiNiuConstants;
import com.dmall.component.file.qiniu.QiNiuFileManager;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.pms.api.enums.BrandErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: 上传售后凭证处理器
 * @author: created by hang.yu on 2020/4/18 12:58
 */
@Slf4j
@Component
public class UploadApplyCredentialsHandler extends AbstractCommonHandler<MultipartFile, OrderDO, UploadResult> {

    @Autowired
    private QiNiuFileManager qiNiuFileManager;

    @Override
    public BaseResult<UploadResult> processor(MultipartFile file) {
        try {
            UploadResult uploadResult = qiNiuFileManager.upload(file, QiNiuConstants.AFTER_SALE_CREDENTIALS);
            return ResultUtil.success(uploadResult);
        } catch (IOException e) {
            log.error("upload credentials failed", e);
            return ResultUtil.fail(BrandErrorEnum.UPLOAD_LOGO_ERROR);
        }
    }
}
