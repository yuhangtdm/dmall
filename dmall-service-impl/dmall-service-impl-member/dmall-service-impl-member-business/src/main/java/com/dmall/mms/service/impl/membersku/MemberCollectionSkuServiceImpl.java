package com.dmall.mms.service.impl.membersku;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.ResponsePage;
import com.dmall.mms.api.dto.membersku.MemberCollectionSkuResponseDTO;
import com.dmall.mms.api.service.MemberSkuService;
import com.dmall.mms.service.impl.membersku.handler.MemberCancelCollectionSkuHandler;
import com.dmall.mms.service.impl.membersku.handler.MemberCollectionSkuHandler;
import com.dmall.mms.service.impl.membersku.handler.PageMemberCollectionSkuHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 会员收藏sku服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@RestController
public class MemberCollectionSkuServiceImpl implements MemberSkuService {

    @Autowired
    private PageMemberCollectionSkuHandler pageMemberCollectionSkuHandler;

    @Autowired
    private MemberCollectionSkuHandler memberCollectionSkuHandler;

    @Autowired
    private MemberCancelCollectionSkuHandler memberCancelCollectionSkuHandler;

    @Override
    public BaseResult<ResponsePage<MemberCollectionSkuResponseDTO>> collectionPage(PageRequestDTO requestDTO) {
        return pageMemberCollectionSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> collectionSku(Long skuId) {
        return memberCollectionSkuHandler.handler(skuId);
    }

    @Override
    public BaseResult<Long> cancel(Long id) {
        return memberCancelCollectionSkuHandler.handler(id);
    }
}
