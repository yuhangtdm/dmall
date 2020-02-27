package com.dmall.mms.service.impl.commentpraise.handler;

import com.dmall.mms.api.dto.commentpraise.common.CommonCommentPraiseResponseDTO;
import com.dmall.mms.service.impl.commentpraise.enums.CommentPraiseErrorEnum;
import com.dmall.mms.generator.dataobject.CommentPraiseDO;
import com.dmall.mms.generator.mapper.CommentPraiseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询评论点赞处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class GetCommentPraiseHandler extends AbstractCommonHandler<Long, CommentPraiseDO, CommonCommentPraiseResponseDTO> {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public BaseResult<CommonCommentPraiseResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonCommentPraiseResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
