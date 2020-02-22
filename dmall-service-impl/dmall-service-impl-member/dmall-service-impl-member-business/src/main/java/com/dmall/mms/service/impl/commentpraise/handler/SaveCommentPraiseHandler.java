package com.dmall.mms.service.impl.commentpraise.handler;

import com.dmall.mms.api.dto.commentpraise.request.SaveCommentPraiseRequestDTO;
import com.dmall.mms.service.impl.commentpraise.enums.CommentPraiseErrorEnum;
import com.dmall.mms.generator.dataobject.CommentPraiseDO;
import com.dmall.mms.generator.mapper.CommentPraiseMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增评论点赞处理器
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Component
public class SaveCommentPraiseHandler extends AbstractCommonHandler<SaveCommentPraiseRequestDTO, CommentPraiseDO, Long> {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public BaseResult<Long> validate(SaveCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
