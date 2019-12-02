package com.dmall.pms.service.impl.skumedia.handler;

import com.dmall.pms.api.dto.skumedia.common.CommonSkuMediaResponseDTO;
import com.dmall.pms.api.dto.skumedia.request.ListSkuMediaRequestDTO;
import com.dmall.pms.service.impl.skumedia.enums.SkuMediaErrorEnum;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMediaMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: sku媒体对象列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class ListSkuMediaHandler extends AbstractCommonHandler<ListSkuMediaRequestDTO, SkuMediaDO, CommonSkuMediaResponseDTO> {

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Override
    public BaseResult<List<CommonSkuMediaResponseDTO>> validate(ListSkuMediaRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonSkuMediaResponseDTO>> processor(ListSkuMediaRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
