package com.dmall.pms.service.impl.commentpraise.handler;

import com.dmall.pms.api.dto.commentpraise.request.UpdateCommentPraiseRequestDTO;
import com.dmall.pms.service.impl.commentpraise.enums.CommentPraiseErrorEnum;
import com.dmall.pms.generator.dataobject.CommentPraiseDO;
import com.dmall.pms.generator.mapper.CommentPraiseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改评论点赞数处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class UpdateCommentPraiseHandler extends AbstractCommonHandler<UpdateCommentPraiseRequestDTO, CommentPraiseDO, Long> {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public BaseResult<Long> validate(UpdateCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
