package com.dmall.pms.service.impl.sku.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuMediaRequestDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import com.dmall.pms.service.impl.support.SkuMediaSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: SaveMediaHandler
 * @author: created by hang.yu on 2019/12/17 14:31
 */
@Component
public class SaveMediaHandler extends AbstractCommonHandler<SaveSkuMediaRequestDTO, SkuMediaDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuMediaSupport skuMediaSupport;

    @Override
    public BaseResult processor(SaveSkuMediaRequestDTO requestDTO) {
        SkuDO skuDO = skuMapper.selectById(requestDTO.getSkuId());
        if (skuDO == null) {
            return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
        }
        if (CollUtil.isEmpty(requestDTO.getMediaList())) {
            return ResultUtil.fail(SkuErrorEnum.MEDIA_NOT_EXIST);
        }
        skuMediaSupport.saveOrDeleteSkuMedia(skuDO.getProductId(), skuDO.getId(), requestDTO.getMediaList());
        SkuDO sku = new SkuDO();
        sku.setId(requestDTO.getSkuId());
        sku.setPic(requestDTO.getMediaList().get(0).getKey());
        skuMapper.updateById(sku);
        return ResultUtil.success(requestDTO.getSkuId());
    }
}
