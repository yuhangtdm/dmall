package com.dmall.mms.service.impl.commentreplypraise.handler;

import com.dmall.mms.api.dto.commentreplypraise.request.SaveCommentReplyPraiseRequestDTO;
import com.dmall.mms.service.impl.commentreplypraise.enums.CommentReplyPraiseErrorEnum;
import com.dmall.mms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.mms.generator.mapper.CommentReplyPraiseMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增回复点赞处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class SaveCommentReplyPraiseHandler extends AbstractCommonHandler<SaveCommentReplyPraiseRequestDTO, CommentReplyPraiseDO, Long> {

    @Autowired
    private CommentReplyPraiseMapper commentReplyPraiseMapper;

    @Override
    public BaseResult<Long> validate(SaveCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
