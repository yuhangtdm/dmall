package com.dmall.mms.service.impl.commentreply;

import com.dmall.mms.api.dto.commentreply.request.SaveCommentReplyRequestDTO;
import com.dmall.mms.api.dto.commentreply.request.UpdateCommentReplyRequestDTO;
import com.dmall.mms.api.dto.commentreply.request.ListCommentReplyRequestDTO;
import com.dmall.mms.api.dto.commentreply.request.PageCommentReplyRequestDTO;
import com.dmall.mms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.mms.api.service.CommentReplyService;
import com.dmall.mms.service.impl.commentreply.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 评价回复服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@RestController
public class CommentReplyServiceImpl implements CommentReplyService {

    @Autowired
    private SaveCommentReplyHandler saveCommentReplyHandler;

    @Autowired
    private DeleteCommentReplyHandler deleteCommentReplyHandler;

    @Autowired
    private UpdateCommentReplyHandler updateCommentReplyHandler;

    @Autowired
    private GetCommentReplyHandler getCommentReplyHandler;

    @Autowired
    private ListCommentReplyHandler listCommentReplyHandler;

    @Autowired
    private PageCommentReplyHandler pageCommentReplyHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveCommentReplyRequestDTO requestDTO) {
        return saveCommentReplyHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteCommentReplyHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateCommentReplyRequestDTO requestDTO) {
        return updateCommentReplyHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonCommentReplyResponseDTO> get(Long id) {
        return getCommentReplyHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonCommentReplyResponseDTO>> list(@RequestBody ListCommentReplyRequestDTO requestDTO) {
        return listCommentReplyHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonCommentReplyResponseDTO>> page(@RequestBody PageCommentReplyRequestDTO requestDTO) {
        return pageCommentReplyHandler.handler(requestDTO);
    }

}
