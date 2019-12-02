package com.dmall.pms.service.impl.commentreply.handler;

import com.dmall.pms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.pms.api.dto.commentreply.request.PageCommentReplyRequestDTO;
import com.dmall.pms.service.impl.commentreply.enums.CommentReplyErrorEnum;
import com.dmall.pms.generator.dataobject.CommentReplyDO;
import com.dmall.pms.generator.mapper.CommentReplyMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 评价回复分页处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class PageCommentReplyHandler extends AbstractCommonHandler<PageCommentReplyRequestDTO, CommentReplyDO, CommonCommentReplyResponseDTO> {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public BaseResult<LayuiPage<CommonCommentReplyResponseDTO>> validate(PageCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonCommentReplyResponseDTO>> processor(PageCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
