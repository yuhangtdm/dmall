package com.dmall.pms.service.impl.skumedia.handler;

import com.dmall.pms.api.dto.skumedia.common.CommonSkuMediaResponseDTO;
import com.dmall.pms.api.dto.skumedia.request.PageSkuMediaRequestDTO;
import com.dmall.pms.service.impl.skumedia.enums.SkuMediaErrorEnum;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMediaMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: sku媒体对象分页处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class PageSkuMediaHandler extends AbstractCommonHandler<PageSkuMediaRequestDTO, SkuMediaDO, CommonSkuMediaResponseDTO> {

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Override
    public BaseResult<LayuiPage<CommonSkuMediaResponseDTO>> validate(PageSkuMediaRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonSkuMediaResponseDTO>> processor(PageSkuMediaRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
