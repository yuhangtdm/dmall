package com.dmall.mms.service.impl.commentreplypraise.handler;

import com.dmall.mms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.mms.service.impl.commentreplypraise.enums.CommentReplyPraiseErrorEnum;
import com.dmall.mms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.mms.generator.mapper.CommentReplyPraiseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询回复点赞处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class GetCommentReplyPraiseHandler extends AbstractCommonHandler<Long, CommentReplyPraiseDO, CommonCommentReplyPraiseResponseDTO> {

    @Autowired
    private CommentReplyPraiseMapper commentReplyPraiseMapper;

    @Override
    public BaseResult<CommonCommentReplyPraiseResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonCommentReplyPraiseResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
