package com.dmall.mms.service.impl.membersku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.feign.SkuServiceFeign;
import com.dmall.mms.generator.dataobject.MemberCollectionSkuDO;
import com.dmall.mms.generator.mapper.MemberCollectionSkuMapper;
import com.dmall.pms.api.dto.sku.response.get.GetSkuResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 收藏sku处理器
 * @author: created by hang.yu on 2020/2/29 17:08
 */
@Component
public class MemberCollectionSkuHandler extends AbstractCommonHandler<Long, MemberCollectionSkuDO, Long> {

    @Autowired
    private SkuServiceFeign skuServiceFeign;

    @Autowired
    private MemberCollectionSkuMapper memberCollectionSkuMapper;

    @Override
    public BaseResult<Long> processor(Long skuId) {
        // 查询sku
        BaseResult<GetSkuResponseDTO> skuResponse = skuServiceFeign.get(skuId);
        if (!skuResponse.getResult()) {
            return ResultUtil.fail(skuResponse.getCode(), skuResponse.getMsg());
        }
        GetSkuResponseDTO data = skuResponse.getData();
        MemberCollectionSkuDO memberCollectionSkuDO = new MemberCollectionSkuDO();
        memberCollectionSkuDO.setSkuId(skuId);
        memberCollectionSkuDO.setProductId(data.getBasicSku().getProductId());
        memberCollectionSkuDO.setProductNo(data.getBasicSku().getProductNo());
        memberCollectionSkuDO.setSkuNo(data.getBasicSku().getSkuNo());
        memberCollectionSkuDO.setPrice(data.getBasicSku().getPrice());
        memberCollectionSkuMapper.insert(memberCollectionSkuDO);

        return ResultUtil.success(memberCollectionSkuDO.getId());
    }
}
