package com.dmall.pms.service.impl.commentreplypraise.handler;

import com.dmall.pms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.ListCommentReplyPraiseRequestDTO;
import com.dmall.pms.generator.dataobject.CommentReplyPraiseDO;
import com.dmall.pms.generator.mapper.CommentReplyPraiseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 回复点赞列表处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
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
