package com.dmall.pms.service.impl.comment;

import com.dmall.common.dto.BaseResult;
import com.dmall.pms.api.dto.comment.request.PageCommentRequestDTO;
import com.dmall.pms.api.dto.comment.request.SaveCommentRequestDTO;
import com.dmall.pms.api.dto.comment.response.CommentPageResponseDTO;
import com.dmall.pms.api.dto.comment.response.CommentResponseDTO;
import com.dmall.pms.api.service.CommentService;
import com.dmall.pms.service.impl.comment.handler.ListCommentHandler;
import com.dmall.pms.service.impl.comment.handler.PageCommentHandler;
import com.dmall.pms.service.impl.comment.handler.SaveCommentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商品评价服务实现
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@RestController
public class CommentServiceImpl implements CommentService {

    @Autowired
    private SaveCommentHandler saveCommentHandler;

    @Autowired
    private ListCommentHandler listCommentHandler;

    @Autowired
    private PageCommentHandler pageCommentHandler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult save(@RequestBody List<SaveCommentRequestDTO> requestDTO) {
        return saveCommentHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<CommentPageResponseDTO>> list(Long subOrderId) {
        return listCommentHandler.handler(subOrderId);
    }

    @Override
    public BaseResult<CommentResponseDTO> page(PageCommentRequestDTO requestDTO) {
        return pageCommentHandler.handler(requestDTO);
    }


}
