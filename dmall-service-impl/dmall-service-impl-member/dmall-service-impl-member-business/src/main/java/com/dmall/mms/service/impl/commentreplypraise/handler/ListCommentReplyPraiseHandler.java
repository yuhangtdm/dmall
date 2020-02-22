package com.dmall.mms.service.impl.commentreplypraise.handler;

import com.dmall.mms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.mms.api.dto.commentreplypraise.request.ListCommentReplyPraiseRequestDTO;
import com.dmall.mms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.mms.generator.mapper.CommentReplyPraiseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 回复点赞列表处理器
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Component
public class ListCommentReplyPraiseHandler extends AbstractCommonHandler<ListCommentReplyPraiseRequestDTO, CommentReplyPraiseDO, CommonCommentReplyPraiseResponseDTO> {

    @Autowired
    private CommentReplyPraiseMapper commentReplyPraiseMapper;

    @Override
    public BaseResult<List<CommonCommentReplyPraiseResponseDTO>> validate(ListCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonCommentReplyPraiseResponseDTO>> processor(ListCommentReplyPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
