package com.dmall.pms.service.impl.sku.handler;

import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.UploadResult;
import com.dmall.component.file.qiniu.QiNiuConstants;
import com.dmall.component.file.qiniu.QiNiuFileManager;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.sku.request.UploadRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @description: 上传sku图片处理器
 * @author: created by hang.yu on 2019/12/17 12:25
 */
@Slf4j
@Component
public class UploadSkuHandler extends AbstractCommonHandler<UploadRequestDTO, SkuMediaDO, UploadResult> {

    @Autowired
    private QiNiuFileManager qiNiuFileManager;

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public BaseResult<List<UploadResult>> processor(UploadRequestDTO requestDTO) {
        try {
            SkuDO skuDO = skuMapper.selectById(requestDTO.getId());
            if (skuDO == null) {
                return ResultUtil.fail(PmsErrorEnum.SKU_NOT_EXISTS);
            }
            List<UploadResult> upload = qiNiuFileManager.upload(requestDTO.getFiles(), QiNiuConstants.SKU);
            return ResultUtil.success(upload);
        } catch (IOException e) {
            log.error("upload sku pics error", e);
            return ResultUtil.fail(PmsErrorEnum.UPLOAD_PRODUCT_ERROR);
        }
    }
}
