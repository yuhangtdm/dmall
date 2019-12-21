package com.dmall.pms.service.impl.commentreply.handler;

import com.dmall.pms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.pms.api.dto.commentreply.request.ListCommentReplyRequestDTO;
import com.dmall.pms.generator.dataobject.CommentReplyDO;
import com.dmall.pms.generator.mapper.CommentReplyMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 评价回复列表处理器
 * @author: created by hang.yu on 2019-12-19 20:57:18
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
