package com.dmall.mms.service.impl.membercollectionsku.handler;

import com.dmall.mms.api.dto.membercollectionsku.common.CommonMemberCollectionSkuResponseDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.ListMemberCollectionSkuRequestDTO;
import com.dmall.mms.generator.dataobject.MemberCollectionSkuDO;
import com.dmall.mms.generator.mapper.MemberCollectionSkuMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员收藏sku列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
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
