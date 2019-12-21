package com.dmall.pms.service.impl.commentreplypraise.handler;

import com.dmall.pms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.PageCommentReplyPraiseRequestDTO;
import com.dmall.pms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.pms.generator.mapper.CommentReplyPraiseMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 回复点赞分页处理器
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Component
public class PageCommentReplyPraiseHandler extends AbstractCommonHandler<PageCommentReplyPraiseRequestDTO, CommentReplyPraiseDO, CommonCommentReplyPraiseResponseDTO> {

    @Autowired
    private CommentReplyPraiseMapper commentReplyPraiseMapper;

    @Override
    public BaseResult<LayuiPage<CommonCommentReplyPraiseResponseDTO>> validate(PageCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonCommentReplyPraiseResponseDTO>> processor(PageCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
