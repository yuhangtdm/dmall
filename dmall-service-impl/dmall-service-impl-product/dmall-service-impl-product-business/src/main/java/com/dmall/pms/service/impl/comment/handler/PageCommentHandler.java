package com.dmall.pms.service.impl.comment.handler;

import com.dmall.pms.api.dto.comment.common.CommonCommentResponseDTO;
import com.dmall.pms.api.dto.comment.request.PageCommentRequestDTO;
import com.dmall.pms.service.impl.comment.enums.CommentErrorEnum;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.generator.mapper.CommentMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 商品评价分页处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class PageCommentHandler extends AbstractCommonHandler<PageCommentRequestDTO, CommentDO, CommonCommentResponseDTO> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<LayuiPage<CommonCommentResponseDTO>> validate(PageCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonCommentResponseDTO>> processor(PageCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
