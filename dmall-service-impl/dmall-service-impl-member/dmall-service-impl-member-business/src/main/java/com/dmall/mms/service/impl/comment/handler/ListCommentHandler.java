package com.dmall.mms.service.impl.comment.handler;

import com.dmall.mms.api.dto.comment.common.CommonCommentResponseDTO;
import com.dmall.mms.api.dto.comment.request.ListCommentRequestDTO;
import com.dmall.mms.generator.dataobject.CommentDO;
import com.dmall.mms.generator.mapper.CommentMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 商品评价列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class ListCommentHandler extends AbstractCommonHandler<ListCommentRequestDTO, CommentDO, CommonCommentResponseDTO> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<List<CommonCommentResponseDTO>> validate(ListCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonCommentResponseDTO>> processor(ListCommentRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
