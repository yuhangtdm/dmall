package com.dmall.pms.service.impl.commentreply.handler;

import com.dmall.pms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.pms.service.impl.commentreply.enums.CommentReplyErrorEnum;
import com.dmall.pms.generator.dataobject.CommentReplyDO;
import com.dmall.pms.generator.mapper.CommentReplyMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询评价回复处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetCommentReplyHandler extends AbstractCommonHandler<Long, CommentReplyDO, CommonCommentReplyResponseDTO> {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public BaseResult<CommonCommentReplyResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonCommentReplyResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
