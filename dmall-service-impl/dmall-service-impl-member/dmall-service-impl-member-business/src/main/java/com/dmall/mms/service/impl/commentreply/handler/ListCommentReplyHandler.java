package com.dmall.mms.service.impl.commentreply.handler;

import com.dmall.mms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.mms.api.dto.commentreply.request.ListCommentReplyRequestDTO;
import com.dmall.mms.generator.dataobject.CommentReplyDO;
import com.dmall.mms.generator.mapper.CommentReplyMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 评价回复列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class ListCommentReplyHandler extends AbstractCommonHandler<ListCommentReplyRequestDTO, CommentReplyDO, CommonCommentReplyResponseDTO> {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public BaseResult<List<CommonCommentReplyResponseDTO>> validate(ListCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonCommentReplyResponseDTO>> processor(ListCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
