package com.dmall.pms.service.impl.comment.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.comment.response.CommentPageResponseDTO;
import com.dmall.pms.generator.dataobject.CommentDO;
import com.dmall.pms.generator.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 商品评价列表处理器
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Component
public class ListCommentHandler extends AbstractCommonHandler<Long, CommentDO, List<CommentPageResponseDTO>> {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResult<List<CommentPageResponseDTO>> processor(Long subOrderId) {
        List<CommentDO> commentList = commentMapper.selectList(Wrappers.<CommentDO>lambdaQuery()
                .eq(CommentDO::getSubOrderId, subOrderId));
        List<CommentPageResponseDTO> collect = commentList.stream().map(commentDO -> {
            CommentPageResponseDTO responseDTO = new CommentPageResponseDTO();
            responseDTO.setId(commentDO.getId());
            responseDTO.setSkuId(commentDO.getSkuId());
            responseDTO.setSubOrderId(commentDO.getSubOrderId());
            responseDTO.setContent(commentDO.getContent());
            responseDTO.setStar(commentDO.getStar());
            responseDTO.setMedias(commentDO.getMedias());
            responseDTO.setCreateTime(commentDO.getGmtCreated());
            return responseDTO;
        }).collect(Collectors.toList());
        return ResultUtil.success(collect);
    }
}
