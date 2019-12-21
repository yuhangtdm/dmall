package com.dmall.pms.service.impl.comment.handler;

import com.dmall.pms.api.dto.comment.request.UpdateCommentRequestDTO;
import com.dmall.pms.service.impl.comment.enums.CommentErrorEnum;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.generator.mapper.CommentMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改商品评价处理器
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Component
public class UpdateCommentHandler extends AbstractCommonHandler<UpdateCommentRequestDTO, CommentDO, Long> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<Long> validate(UpdateCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
