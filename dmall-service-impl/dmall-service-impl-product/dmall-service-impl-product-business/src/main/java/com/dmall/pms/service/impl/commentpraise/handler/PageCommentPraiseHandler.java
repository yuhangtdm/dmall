package com.dmall.pms.service.impl.commentpraise.handler;

import com.dmall.pms.api.dto.commentpraise.common.CommonCommentPraiseResponseDTO;
import com.dmall.pms.api.dto.commentpraise.request.PageCommentPraiseRequestDTO;
import com.dmall.pms.generator.dataobject.CommentPraiseDO;
import com.dmall.pms.generator.mapper.CommentPraiseMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 评论点赞数分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class PageCommentPraiseHandler extends AbstractCommonHandler<PageCommentPraiseRequestDTO, CommentPraiseDO, CommonCommentPraiseResponseDTO> {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public BaseResult<LayuiPage<CommonCommentPraiseResponseDTO>> validate(PageCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonCommentPraiseResponseDTO>> processor(PageCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
