package com.dmall.pms.service.impl.comment.handler;

import com.dmall.pms.api.dto.comment.common.CommonCommentResponseDTO;
import com.dmall.pms.api.dto.comment.request.ListCommentRequestDTO;
import com.dmall.pms.service.impl.comment.enums.CommentErrorEnum;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.generator.mapper.CommentMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 商品评价列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
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
