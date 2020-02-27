package com.dmall.mms.service.impl.commentreplypraise.handler;

import com.dmall.mms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.mms.api.dto.commentreplypraise.request.PageCommentReplyPraiseRequestDTO;
import com.dmall.mms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.mms.generator.mapper.CommentReplyPraiseMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 回复点赞分页处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class PageCommentReplyPraiseHandler extends AbstractCommonHandler<PageCommentReplyPraiseRequestDTO, CommentReplyPraiseDO, CommonCommentReplyPraiseResponseDTO> {

    @Autowired
    private CommentReplyPraiseMapper commentReplyPraiseMapper;

    @Override
    public BaseResult<LayUiPage<CommonCommentReplyPraiseResponseDTO>> validate(PageCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonCommentReplyPraiseResponseDTO>> processor(PageCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
