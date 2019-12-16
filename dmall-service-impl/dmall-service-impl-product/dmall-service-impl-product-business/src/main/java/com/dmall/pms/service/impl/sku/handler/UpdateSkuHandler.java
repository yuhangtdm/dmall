package com.dmall.pms.service.impl.sku.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.pms.api.dto.sku.request.update.BasicSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.update.UpdateSkuRequestDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuExtDO;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @description: 修改sku处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class UpdateSkuHandler extends AbstractCommonHandler<UpdateSkuRequestDTO, SkuDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SaveSkuAttributeHandler saveSkuAttributeHandler;

    @Autowired
    private SkuExtMapper skuExtMapper;

    @Override
    public BaseResult<Long> validate(UpdateSkuRequestDTO requestDTO) {
        BasicSkuRequestDTO basicSku = requestDTO.getBasicSkuRequestDTO();
        SkuDO sku = skuMapper.selectById(basicSku.getId());
        if (sku == null) {
            return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
        }
        // 名称必须唯一
        SkuDO skuDO = skuMapper.selectOne(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getName, basicSku.getName()));
        if (skuDO != null && !skuDO.getId().equals(sku.getId())) {
            return ResultUtil.fail(SkuErrorEnum.SKU_NAME_EXISTS);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateSkuRequestDTO requestDTO) {
        BasicSkuRequestDTO basicSku = requestDTO.getBasicSkuRequestDTO();
        SkuDO skuDO = BeanUtil.copyProperties(basicSku, SkuDO.class);
        skuMapper.updateById(skuDO);
        saveSkuAttributeHandler.setProductAttribute(basicSku.getId(), requestDTO.getAttributeValueList());
        SkuExtDO skuExtDO = skuExtMapper.selectOne(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, basicSku.getId()));
        skuExtDO.setDetailHtml(requestDTO.getDetailHtml());
        skuExtDO.setDetailMobileHtml(requestDTO.getDetailMobileHtml());
        skuExtMapper.updateById(skuExtDO);
        return ResultUtil.success();
    }

}
