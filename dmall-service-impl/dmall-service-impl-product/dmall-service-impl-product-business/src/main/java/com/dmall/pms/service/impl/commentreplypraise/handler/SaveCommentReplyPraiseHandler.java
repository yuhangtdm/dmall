package com.dmall.pms.service.impl.commentreplypraise.handler;

import com.dmall.pms.api.dto.commentreplypraise.request.SaveCommentReplyPraiseRequestDTO;
import com.dmall.pms.service.impl.commentreplypraise.enums.CommentReplyPraiseErrorEnum;
import com.dmall.pms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.pms.generator.mapper.CommentReplyPraiseMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增回复点赞处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
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
