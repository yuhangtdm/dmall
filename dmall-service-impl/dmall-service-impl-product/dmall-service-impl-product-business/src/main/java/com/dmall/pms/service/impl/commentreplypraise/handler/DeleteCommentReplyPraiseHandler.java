package com.dmall.pms.service.impl.commentreplypraise.handler;

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
 * @description: 删除回复点赞处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class DeleteCommentReplyPraiseHandler extends AbstractCommonHandler<Long, CommentReplyPraiseDO, Long> {

    @Autowired
    private CommentReplyPraiseMapper commentReplyPraiseMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}
