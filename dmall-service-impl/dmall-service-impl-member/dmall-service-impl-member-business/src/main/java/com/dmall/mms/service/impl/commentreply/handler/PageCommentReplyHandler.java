package com.dmall.mms.service.impl.commentreply.handler;

import com.dmall.mms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.mms.api.dto.commentreply.request.PageCommentReplyRequestDTO;
import com.dmall.mms.generator.dataobject.CommentReplyDO;
import com.dmall.mms.generator.mapper.CommentReplyMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 评价回复分页处理器
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Component
public class PageCommentReplyHandler extends AbstractCommonHandler<PageCommentReplyRequestDTO, CommentReplyDO, CommonCommentReplyResponseDTO> {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public BaseResult<LayUiPage<CommonCommentReplyResponseDTO>> validate(PageCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonCommentReplyResponseDTO>> processor(PageCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
