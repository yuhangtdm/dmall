package com.dmall.pms.service.impl.comment.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.comment.request.PageCommentRequestDTO;
import com.dmall.pms.api.dto.comment.response.CommentResponseDTO;
import com.dmall.pms.api.enums.CommentEnum;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.service.impl.comment.mapper.SkuCommentMapper;
import com.dmall.pms.service.support.CommentSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 商品评价列表处理器
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Component
public class PageCommentHandler extends AbstractCommonHandler<PageCommentRequestDTO, CommentDO, CommentResponseDTO> {

    @Autowired
    private SkuCommentMapper skuCommentMapper;

    @Autowired
    private CommentSupport commentSupport;

    @Override
    public BaseResult<CommentResponseDTO> processor(PageCommentRequestDTO requestDTO) {
        Page page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        skuCommentMapper.page(page, requestDTO);
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setPage(new ResponsePage<>(page.getTotal(), page.getRecords()));
        if (requestDTO.getSkuId() != null){
            Long skuId = requestDTO.getSkuId();
            commentResponseDTO.setCommentCount(commentSupport.countByProductId(skuId));
            commentResponseDTO.setGoodCommentCount(commentSupport.countBySkuId(skuId, CommentEnum.GOOD.getCode()));
            commentResponseDTO.setMiddleCommentCount(commentSupport.countBySkuId(skuId, CommentEnum.MIDDLE.getCode()));
            commentResponseDTO.setBadCommentCount(commentSupport.countBySkuId(skuId, CommentEnum.BAD.getCode()));
            commentResponseDTO.setHasPicCommentCount(commentSupport.countBySkuIdHasPic(skuId));
        }else {
            Long productId = requestDTO.getProductId();
            commentResponseDTO.setCommentCount(commentSupport.countByProductId(productId));
            commentResponseDTO.setGoodCommentCount(commentSupport.countByProductId(productId, CommentEnum.GOOD.getCode()));
            commentResponseDTO.setMiddleCommentCount(commentSupport.countByProductId(productId, CommentEnum.MIDDLE.getCode()));
            commentResponseDTO.setBadCommentCount(commentSupport.countByProductId(productId, CommentEnum.BAD.getCode()));
            commentResponseDTO.setHasPicCommentCount(commentSupport.countByProductIdHasPic(productId));
        }
        return ResultUtil.success(commentResponseDTO);
    }
}
