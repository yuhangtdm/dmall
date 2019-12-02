package com.dmall.pms.service.impl.commentreply.handler;

import com.dmall.pms.api.dto.commentreply.request.SaveCommentReplyRequestDTO;
import com.dmall.pms.service.impl.commentreply.enums.CommentReplyErrorEnum;
import com.dmall.pms.generator.dataobject.CommentReplyDO;
import com.dmall.pms.generator.mapper.CommentReplyMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增评价回复处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
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
