package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.UploadResult;
import com.dmall.component.file.qiniu.QiNiuConstants;
import com.dmall.component.file.qiniu.QiNiuFileManager;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: UploadProductPicHandler
 * @author: created by hang.yu on 2019/12/17 12:08
 */
@Slf4j
@Component
public class UploadProductPicHandler extends AbstractCommonHandler<MultipartFile, BrandDO, UploadResult> {

    @Autowired
    private QiNiuFileManager qiNiuFileManager;

    @Override
    public BaseResult<UploadResult> processor(MultipartFile file) {
        try {
            UploadResult uploadResult = qiNiuFileManager.upload(file, QiNiuConstants.PRODUCT);
            return ResultUtil.success(uploadResult);
        } catch (IOException e) {
            log.error("upload product pic failed", e);
            return ResultUtil.success(ProductErrorEnum.UPLOAD_PRODUCT_ERROR);
        }
    }
}
