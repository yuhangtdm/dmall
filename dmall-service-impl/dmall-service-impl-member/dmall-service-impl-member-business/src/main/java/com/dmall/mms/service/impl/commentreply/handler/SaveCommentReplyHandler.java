package com.dmall.mms.service.impl.commentreply.handler;

import com.dmall.mms.api.dto.commentreply.request.SaveCommentReplyRequestDTO;
import com.dmall.mms.service.impl.commentreply.enums.CommentReplyErrorEnum;
import com.dmall.mms.generator.dataobject.CommentReplyDO;
import com.dmall.mms.generator.mapper.CommentReplyMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增评价回复处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class SaveCommentReplyHandler extends AbstractCommonHandler<SaveCommentReplyRequestDTO, CommentReplyDO, Long> {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public BaseResult<Long> validate(SaveCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
