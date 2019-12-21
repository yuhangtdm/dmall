package com.dmall.pms.service.impl.commentreply.handler;

import com.dmall.pms.service.impl.commentreply.enums.CommentReplyErrorEnum;
import com.dmall.pms.generator.dataobject.CommentReplyDO;
import com.dmall.pms.generator.mapper.CommentReplyMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除评价回复处理器
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Component
public class DeleteCommentReplyHandler extends AbstractCommonHandler<Long, CommentReplyDO, Long> {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}
