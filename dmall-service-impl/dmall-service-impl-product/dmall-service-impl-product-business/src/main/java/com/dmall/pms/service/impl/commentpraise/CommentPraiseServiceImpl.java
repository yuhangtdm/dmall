package com.dmall.pms.service.impl.commentpraise;

import com.dmall.pms.api.dto.commentpraise.request.SaveCommentPraiseRequestDTO;
import com.dmall.pms.api.dto.commentpraise.request.UpdateCommentPraiseRequestDTO;
import com.dmall.pms.api.dto.commentpraise.request.ListCommentPraiseRequestDTO;
import com.dmall.pms.api.dto.commentpraise.request.PageCommentPraiseRequestDTO;
import com.dmall.pms.api.dto.commentpraise.common.CommonCommentPraiseResponseDTO;
import com.dmall.pms.api.service.CommentPraiseService;
import com.dmall.pms.service.impl.commentpraise.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 评论点赞数服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@RestController
public class  CommentPraiseServiceImpl implements CommentPraiseService {

    @Autowired
    protected SaveCommentPraiseHandler saveCommentPraiseHandler;

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
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteCommentPraiseHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateCommentPraiseRequestDTO requestDTO) {
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
    public BaseResult<LayuiPage<CommonCommentPraiseResponseDTO>> page(@RequestBody PageCommentPraiseRequestDTO requestDTO) {
        return pageCommentPraiseHandler.handler(requestDTO);
    }

}
