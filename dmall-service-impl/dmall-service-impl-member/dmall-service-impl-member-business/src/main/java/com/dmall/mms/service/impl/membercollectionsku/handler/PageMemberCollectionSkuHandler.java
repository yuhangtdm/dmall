package com.dmall.mms.service.impl.membercollectionsku.handler;

import com.dmall.mms.api.dto.membercollectionsku.common.CommonMemberCollectionSkuResponseDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.PageMemberCollectionSkuRequestDTO;
import com.dmall.mms.generator.dataobject.MemberCollectionSkuDO;
import com.dmall.mms.generator.mapper.MemberCollectionSkuMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员收藏sku分页处理器
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Component
public class PageMemberCollectionSkuHandler extends AbstractCommonHandler<PageMemberCollectionSkuRequestDTO, MemberCollectionSkuDO, CommonMemberCollectionSkuResponseDTO> {

    @Autowired
    private MemberCollectionSkuMapper memberCollectionSkuMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberCollectionSkuResponseDTO>> validate(PageMemberCollectionSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberCollectionSkuResponseDTO>> processor(PageMemberCollectionSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
