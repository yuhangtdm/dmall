package com.dmall.pms.service.impl.commentreplypraise;

import com.dmall.pms.api.dto.commentreplypraise.request.SaveCommentReplyPraiseRequestDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.UpdateCommentReplyPraiseRequestDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.ListCommentReplyPraiseRequestDTO;
import com.dmall.pms.api.dto.commentreplypraise.request.PageCommentReplyPraiseRequestDTO;
import com.dmall.pms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseResponseDTO;
import com.dmall.pms.api.service.CommentReplyPraiseService;
import com.dmall.pms.service.impl.commentreplypraise.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 回复点赞服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@RestController
public class  CommentReplyPraiseServiceImpl implements CommentReplyPraiseService {

    @Autowired
    protected SaveCommentReplyPraiseHandler saveCommentReplyPraiseHandler;

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
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteCommentReplyPraiseHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateCommentReplyPraiseRequestDTO requestDTO) {
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
    public BaseResult<LayuiPage<CommonCommentReplyPraiseResponseDTO>> page(@RequestBody PageCommentReplyPraiseRequestDTO requestDTO) {
        return pageCommentReplyPraiseHandler.handler(requestDTO);
    }

}
