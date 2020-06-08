package com.dmall.mms.service.impl.membersku.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.membersku.MemberCollectionSkuResponseDTO;
import com.dmall.mms.feign.SkuServiceFeign;
import com.dmall.mms.generator.dataobject.MemberCollectionSkuDO;
import com.dmall.mms.generator.mapper.MemberCollectionSkuMapper;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.dmall.pms.api.dto.sku.response.get.GetSkuResponseDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 会员收藏sku分页处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class PageMemberCollectionSkuHandler
    extends AbstractCommonHandler<PageRequestDTO, MemberCollectionSkuDO, MemberCollectionSkuResponseDTO> {

    @Autowired
    private MemberCollectionSkuMapper memberCollectionSkuMapper;

    @Autowired
    private SkuServiceFeign skuServiceFeign;

    private static final String PRICE_REDUCTION_TAG = "比收藏时降价{}元";

    @Override
    public BaseResult<ResponsePage<MemberCollectionSkuResponseDTO>> processor(PageRequestDTO requestDTO) {
        // 获取当前登录会员
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();

        LambdaQueryWrapper<MemberCollectionSkuDO> wrapper = Wrappers.<MemberCollectionSkuDO>lambdaQuery()
            .eq(MemberCollectionSkuDO::getCreator, loginMember.getId())
            .orderByDesc(MemberCollectionSkuDO::getCreator);

        IPage<MemberCollectionSkuDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        page = memberCollectionSkuMapper.selectPage(page, wrapper);
        List<MemberCollectionSkuResponseDTO> collect = page.getRecords().stream()
            .map(memberCollectionSkuDO -> doConvertDto(memberCollectionSkuDO, MemberCollectionSkuResponseDTO.class))
            .collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage<>(page.getTotal(), collect));
    }

    @Override
    protected void customerConvertDto(MemberCollectionSkuResponseDTO result, MemberCollectionSkuDO doo) {
        BaseResult<GetSkuResponseDTO> skuResponse = skuServiceFeign.get(doo.getSkuId());
        if (PmsErrorEnum.SKU_NOT_EXISTS.getCode().equals(skuResponse.getCode())) {
            result.setSkuStatus(YNEnum.N.getCode());
            return;
        }
        if (!skuResponse.getResult()) {
            throw new BusinessException(skuResponse.getCode(), skuResponse.getMsg());
        }
        BasicSkuResponseDTO basicSku = skuResponse.getData().getBasicSku();
        result.setCurrentPrice(basicSku.getPrice());
        result.setSkuName(basicSku.getName());
        result.setSkuStatus(YNEnum.Y.getCode());
        if (result.getPrice().compareTo(basicSku.getPrice()) < 0) {
            result.setPriceReductionTag(StrUtil.format(PRICE_REDUCTION_TAG,
                result.getPrice().subtract(basicSku.getPrice())));
        }
    }

}
