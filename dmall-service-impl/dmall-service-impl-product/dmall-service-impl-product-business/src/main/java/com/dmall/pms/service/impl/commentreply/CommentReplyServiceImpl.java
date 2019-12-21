package com.dmall.pms.service.impl.commentreply;

import com.dmall.pms.api.dto.commentreply.request.SaveCommentReplyRequestDTO;
import com.dmall.pms.api.dto.commentreply.request.UpdateCommentReplyRequestDTO;
import com.dmall.pms.api.dto.commentreply.request.ListCommentReplyRequestDTO;
import com.dmall.pms.api.dto.commentreply.request.PageCommentReplyRequestDTO;
import com.dmall.pms.api.dto.commentreply.common.CommonCommentReplyResponseDTO;
import com.dmall.pms.api.service.CommentReplyService;
import com.dmall.pms.service.impl.commentreply.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 评价回复服务实现
 * @author: created by hang.yu on 2019-12-19 20:57:18
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
    public BaseResult<LayuiPage<CommonCommentReplyResponseDTO>> page(@RequestBody PageCommentReplyRequestDTO requestDTO) {
        return pageCommentReplyHandler.handler(requestDTO);
    }

}
