package com.dmall.mms.service.impl.comment;

import com.dmall.mms.api.dto.comment.request.SaveCommentRequestDTO;
import com.dmall.mms.api.dto.comment.request.UpdateCommentRequestDTO;
import com.dmall.mms.api.dto.comment.request.ListCommentRequestDTO;
import com.dmall.mms.api.dto.comment.request.PageCommentRequestDTO;
import com.dmall.mms.api.dto.comment.common.CommonCommentResponseDTO;
import com.dmall.mms.api.service.CommentService;
import com.dmall.mms.service.impl.comment.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 商品评价服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@RestController
public class CommentServiceImpl implements CommentService {

    @Autowired
    private SaveCommentHandler saveCommentHandler;

    @Autowired
    private DeleteCommentHandler deleteCommentHandler;

    @Autowired
    private UpdateCommentHandler updateCommentHandler;

    @Autowired
    private GetCommentHandler getCommentHandler;

    @Autowired
    private ListCommentHandler listCommentHandler;

    @Autowired
    private PageCommentHandler pageCommentHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveCommentRequestDTO requestDTO) {
        return saveCommentHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteCommentHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateCommentRequestDTO requestDTO) {
        return updateCommentHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonCommentResponseDTO> get(Long id) {
        return getCommentHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonCommentResponseDTO>> list(@RequestBody ListCommentRequestDTO requestDTO) {
        return listCommentHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonCommentResponseDTO>> page(@RequestBody PageCommentRequestDTO requestDTO) {
        return pageCommentHandler.handler(requestDTO);
    }

}
