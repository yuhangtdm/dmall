package com.dmall.mms.service.impl.commentpraise;

import com.dmall.mms.api.dto.commentpraise.request.SaveCommentPraiseRequestDTO;
import com.dmall.mms.api.dto.commentpraise.request.UpdateCommentPraiseRequestDTO;
import com.dmall.mms.api.dto.commentpraise.request.ListCommentPraiseRequestDTO;
import com.dmall.mms.api.dto.commentpraise.request.PageCommentPraiseRequestDTO;
import com.dmall.mms.api.dto.commentpraise.common.CommonCommentPraiseResponseDTO;
import com.dmall.mms.api.service.CommentPraiseService;
import com.dmall.mms.service.impl.commentpraise.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 评论点赞服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@RestController
public class CommentPraiseServiceImpl implements CommentPraiseService {

    @Autowired
    private SaveCommentPraiseHandler saveCommentPraiseHandler;

    @Autowired
    private DeleteCommentPraiseHandler deleteCommentPraiseHandler;

    @Autowired
    private UpdateCommentPraiseHandler updateCommentPraiseHandler;

    @Autowired
    private GetCommentPraiseHandler getCommentPraiseHandler;

    @Autowired
    private ListCommentPraiseHandler listCommentPraiseHandler;

    @Autowired
    private PageCommentPraiseHandler pageCommentPraiseHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveCommentPraiseRequestDTO requestDTO) {
        return saveCommentPraiseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteCommentPraiseHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateCommentPraiseRequestDTO requestDTO) {
        return updateCommentPraiseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonCommentPraiseResponseDTO> get(Long id) {
        return getCommentPraiseHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonCommentPraiseResponseDTO>> list(@RequestBody ListCommentPraiseRequestDTO requestDTO) {
        return listCommentPraiseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonCommentPraiseResponseDTO>> page(@RequestBody PageCommentPraiseRequestDTO requestDTO) {
        return pageCommentPraiseHandler.handler(requestDTO);
    }

}
