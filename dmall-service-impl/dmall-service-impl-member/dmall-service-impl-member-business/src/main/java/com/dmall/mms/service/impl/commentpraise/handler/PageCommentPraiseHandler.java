package com.dmall.mms.service.impl.commentpraise.handler;

import com.dmall.mms.api.dto.commentpraise.common.CommonCommentPraiseResponseDTO;
import com.dmall.mms.api.dto.commentpraise.request.PageCommentPraiseRequestDTO;
import com.dmall.mms.generator.dataobject.CommentPraiseDO;
import com.dmall.mms.generator.mapper.CommentPraiseMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 评论点赞分页处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class PageCommentPraiseHandler extends AbstractCommonHandler<PageCommentPraiseRequestDTO, CommentPraiseDO, CommonCommentPraiseResponseDTO> {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public BaseResult<LayUiPage<CommonCommentPraiseResponseDTO>> validate(PageCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonCommentPraiseResponseDTO>> processor(PageCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
