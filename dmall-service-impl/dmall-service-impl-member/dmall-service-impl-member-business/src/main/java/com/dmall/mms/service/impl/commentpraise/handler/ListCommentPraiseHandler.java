package com.dmall.mms.service.impl.commentpraise.handler;

import com.dmall.mms.api.dto.commentpraise.common.CommonCommentPraiseResponseDTO;
import com.dmall.mms.api.dto.commentpraise.request.ListCommentPraiseRequestDTO;
import com.dmall.mms.generator.dataobject.CommentPraiseDO;
import com.dmall.mms.generator.mapper.CommentPraiseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 评论点赞列表处理器
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Component
public class ListCommentPraiseHandler extends AbstractCommonHandler<ListCommentPraiseRequestDTO, CommentPraiseDO, CommonCommentPraiseResponseDTO> {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public BaseResult<List<CommonCommentPraiseResponseDTO>> validate(ListCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonCommentPraiseResponseDTO>> processor(ListCommentPraiseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
