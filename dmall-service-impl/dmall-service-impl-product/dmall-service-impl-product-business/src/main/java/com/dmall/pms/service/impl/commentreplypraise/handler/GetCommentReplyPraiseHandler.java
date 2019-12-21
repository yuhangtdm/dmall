package com.dmall.pms.service.impl.commentreplypraise.handler;

import com.dmall.pms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.pms.service.impl.commentreplypraise.enums.CommentReplyPraiseErrorEnum;
import com.dmall.pms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.pms.generator.mapper.CommentReplyPraiseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询回复点赞处理器
 * @author: created by hang.yu on 2019-12-19 20:57:18
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
