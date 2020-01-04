package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.UploadResult;
import com.dmall.component.file.qiniu.QiNiuConstants;
import com.dmall.component.file.qiniu.QiNiuFileManager;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.sku.request.UploadRequestDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.generator.mapper.SkuMediaMapper;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 12:25
 */
@Slf4j
@Component
public class UploadSkuHandler extends AbstractCommonHandler<UploadRequestDTO, SkuMediaDO, UploadResult> {

    @Autowired
    private QiNiuFileManager qiNiuFileManager;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Override
    public BaseResult<UploadResult> processor(UploadRequestDTO requestDTO) {
        try {
            SkuDO skuDO = skuMapper.selectById(requestDTO.getId());
            if (skuDO == null) {
                return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
            }
            UploadResult uploadResult = qiNiuFileManager.upload(requestDTO.getFile(), QiNiuConstants.SKU);
            return ResultUtil.success(uploadResult);
        } catch (IOException e) {
            log.error("upload sku pic failed" , e);
            return ResultUtil.success(ProductErrorEnum.UPLOAD_PRODUCT_ERROR);
        }
    }
}
