package com.dmall.pms.service.impl.comment.handler;

import com.dmall.pms.api.dto.comment.request.SaveCommentRequestDTO;
import com.dmall.pms.service.impl.comment.enums.CommentErrorEnum;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.generator.mapper.CommentMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增商品评价处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class SaveCommentHandler extends AbstractCommonHandler<SaveCommentRequestDTO, CommentDO, Long> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<Long> validate(SaveCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
