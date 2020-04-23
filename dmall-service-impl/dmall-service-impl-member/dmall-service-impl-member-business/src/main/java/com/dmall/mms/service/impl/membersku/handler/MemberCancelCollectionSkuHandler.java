package com.dmall.mms.service.impl.membersku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberCollectionSkuDO;
import com.dmall.mms.generator.mapper.MemberCollectionSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 取消收藏sku处理器
 * @author: created by hang.yu on 2020/2/29 17:08
 */
@Component
public class MemberCancelCollectionSkuHandler extends AbstractCommonHandler<Long, MemberCollectionSkuDO, Long> {

    @Autowired
    private MemberCollectionSkuMapper memberCollectionSkuMapper;

    @Override
    public BaseResult<Long> processor(Long id) {
        MemberCollectionSkuDO memberCollectionSkuDO = memberCollectionSkuMapper.selectById(id);
        if (memberCollectionSkuDO == null) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_COLLECTION_SKU_NOT_EXIST);
        }
        // 获取当前登录会员
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        if (!memberCollectionSkuDO.getCreator().equals(loginMember.getId())) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_COLLECTION_SKU_ERROR);
        }
        memberCollectionSkuMapper.deleteById(id);
        return ResultUtil.success(id);
    }
}
