package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.service.impl.user.enums.UserErrorEnum;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.UploadResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.file.qiniu.QiNiuConstants;
import com.dmall.component.file.qiniu.QiNiuFileManager;
import com.dmall.component.web.handler.AbstractCommonHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: 后台用户列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Slf4j
@Component
public class UploadIconHandler extends AbstractCommonHandler<MultipartFile, UserDO, UploadResult> {

    @Autowired
    private QiNiuFileManager qiNiuFileManager;


    @Override
    public BaseResult<UploadResult> processor(MultipartFile file) {
        try {
            UploadResult uploadResult = qiNiuFileManager.upload(file, QiNiuConstants.ADMIN_ICON);
            return ResultUtil.success(uploadResult);
        } catch (IOException e) {
            log.error("upload admin icon failed", e);
            return ResultUtil.success(UserErrorEnum.UPLOAD_PIC_ERROR);
        }
    }

}
