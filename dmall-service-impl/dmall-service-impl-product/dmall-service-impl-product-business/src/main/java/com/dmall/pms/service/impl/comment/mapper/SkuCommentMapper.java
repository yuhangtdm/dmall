package com.dmall.pms.service.impl.comment.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.pms.api.dto.comment.request.PageCommentRequestDTO;
import com.dmall.pms.api.dto.comment.response.CommentPageResponseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: SkuCommentMapper
 * @author: created by hang.yu on 2020/4/25 16:48
 */
public interface SkuCommentMapper {

    List<CommentPageResponseDTO> page(Page page, @Param("request")PageCommentRequestDTO requestDTO);
}
