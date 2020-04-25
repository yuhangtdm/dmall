package com.dmall.pms.service.impl.comment.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.comment.request.SaveCommentRequestDTO;
import com.dmall.pms.api.enums.CommentLevelEnum;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.CommentMapper;
import com.dmall.pms.service.support.SkuImportEsSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 新增商品评价处理器
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Component
public class SaveCommentHandler extends AbstractCommonHandler<List<SaveCommentRequestDTO>, CommentDO, Void> {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private SkuImportEsSupport skuImportEsSupport;

    @Override
    public BaseResult processor(List<SaveCommentRequestDTO> requestDTO) {
        for (SaveCommentRequestDTO saveCommentRequestDTO : requestDTO) {
            SkuDO skuDO = pmsValidate.validateSku(saveCommentRequestDTO.getSkuId());
            CommentDO commentDO = new CommentDO();
            commentDO.setProductId(skuDO.getProductId());
            commentDO.setSkuId(skuDO.getId());
            commentDO.setOrderId(saveCommentRequestDTO.getOrderId());
            commentDO.setSubOrderId(saveCommentRequestDTO.getSubOrderId());
            PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
            commentDO.setMemberNickName(portalMemberDTO.getNickName());
            commentDO.setMemberIcon(portalMemberDTO.getIcon());
            commentDO.setContent(saveCommentRequestDTO.getContent());
            commentDO.setStar(saveCommentRequestDTO.getStar());
            commentDO.setLevel(EnumUtil.getData(CommentLevelEnum.class, saveCommentRequestDTO.getStar()));
            commentDO.setMedias(saveCommentRequestDTO.getMedias());
            commentMapper.insert(commentDO);
            skuImportEsSupport.sendSyncSkuMq(skuDO.getId());
        }
        return ResultUtil.success();
    }

}
