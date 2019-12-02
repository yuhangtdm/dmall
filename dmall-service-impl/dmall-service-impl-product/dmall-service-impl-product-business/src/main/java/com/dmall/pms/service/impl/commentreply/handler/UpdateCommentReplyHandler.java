package com.dmall.pms.service.impl.commentreply.handler;

import com.dmall.pms.api.dto.commentreply.request.UpdateCommentReplyRequestDTO;
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
 * @description: 修改评价回复处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class UpdateCommentReplyHandler extends AbstractCommonHandler<UpdateCommentReplyRequestDTO, CommentReplyDO, Long> {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public BaseResult<Long> validate(UpdateCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateCommentReplyRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
