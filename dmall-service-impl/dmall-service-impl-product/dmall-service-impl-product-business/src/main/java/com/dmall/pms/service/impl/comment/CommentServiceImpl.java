package com.dmall.pms.service.impl.comment;

import com.dmall.pms.api.dto.comment.request.SaveCommentRequestDTO;
import com.dmall.pms.api.dto.comment.request.UpdateCommentRequestDTO;
import com.dmall.pms.api.dto.comment.request.ListCommentRequestDTO;
import com.dmall.pms.api.dto.comment.request.PageCommentRequestDTO;
import com.dmall.pms.api.dto.comment.common.CommonCommentResponseDTO;
import com.dmall.pms.api.service.CommentService;
import com.dmall.pms.service.impl.comment.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商品评价服务实现
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@RestController
public class  CommentServiceImpl implements CommentService {

    @Autowired
    protected SaveCommentHandler saveCommentHandler;

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
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteCommentHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateCommentRequestDTO requestDTO) {
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
    public BaseResult<LayuiPage<CommonCommentResponseDTO>> page(@RequestBody PageCommentRequestDTO requestDTO) {
        return pageCommentHandler.handler(requestDTO);
    }

}
