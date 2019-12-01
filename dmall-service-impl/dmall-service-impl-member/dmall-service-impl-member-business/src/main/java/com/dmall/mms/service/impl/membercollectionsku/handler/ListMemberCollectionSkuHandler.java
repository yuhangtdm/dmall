package com.dmall.mms.service.impl.membercollectionsku.handler;

import com.dmall.mms.api.dto.membercollectionsku.common.CommonMemberCollectionSkuResponseDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.ListMemberCollectionSkuRequestDTO;
import com.dmall.mms.service.impl.membercollectionsku.enums.MemberCollectionSkuErrorEnum;
import com.dmall.mms.generator.dataobject.MemberCollectionSkuDO;
import com.dmall.mms.generator.mapper.MemberCollectionSkuMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员收藏sku列表处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class ListMemberCollectionSkuHandler extends AbstractCommonHandler<ListMemberCollectionSkuRequestDTO, MemberCollectionSkuDO, CommonMemberCollectionSkuResponseDTO> {

    @Autowired
    private MemberCollectionSkuMapper memberCollectionSkuMapper;

    @Override
    public BaseResult<List<CommonMemberCollectionSkuResponseDTO>> validate(ListMemberCollectionSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberCollectionSkuResponseDTO>> processor(ListMemberCollectionSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
