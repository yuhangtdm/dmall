package com.dmall.mms.service.impl.commentreplypraise;

import com.dmall.mms.api.dto.commentreplypraise.request.SaveCommentReplyPraiseRequestDTO;
import com.dmall.mms.api.dto.commentreplypraise.request.UpdateCommentReplyPraiseRequestDTO;
import com.dmall.mms.api.dto.commentreplypraise.request.ListCommentReplyPraiseRequestDTO;
import com.dmall.mms.api.dto.commentreplypraise.request.PageCommentReplyPraiseRequestDTO;
import com.dmall.mms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.mms.api.service.CommentReplyPraiseService;
import com.dmall.mms.service.impl.commentreplypraise.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 回复点赞服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@RestController
public class CommentReplyPraiseServiceImpl implements CommentReplyPraiseService {

    @Autowired
    private SaveCommentReplyPraiseHandler saveCommentReplyPraiseHandler;

    @Autowired
    private DeleteCommentReplyPraiseHandler deleteCommentReplyPraiseHandler;

    @Autowired
    private UpdateCommentReplyPraiseHandler updateCommentReplyPraiseHandler;

    @Autowired
    private GetCommentReplyPraiseHandler getCommentReplyPraiseHandler;

    @Autowired
    private ListCommentReplyPraiseHandler listCommentReplyPraiseHandler;

    @Autowired
    private PageCommentReplyPraiseHandler pageCommentReplyPraiseHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveCommentReplyPraiseRequestDTO requestDTO) {
        return saveCommentReplyPraiseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteCommentReplyPraiseHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateCommentReplyPraiseRequestDTO requestDTO) {
        return updateCommentReplyPraiseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonCommentReplyPraiseResponseDTO> get(Long id) {
        return getCommentReplyPraiseHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonCommentReplyPraiseResponseDTO>> list(@RequestBody ListCommentReplyPraiseRequestDTO requestDTO) {
        return listCommentReplyPraiseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonCommentReplyPraiseResponseDTO>> page(@RequestBody PageCommentReplyPraiseRequestDTO requestDTO) {
        return pageCommentReplyPraiseHandler.handler(requestDTO);
    }

}
