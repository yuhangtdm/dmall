package com.dmall.oms.service.impl.order.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.oms.service.impl.order.mapper.dto.commentpage.CommentPageDbDTO;
import com.dmall.oms.service.impl.order.mapper.dto.commentpage.CommentPageRequestDbDTO;
import com.dmall.oms.service.impl.order.mapper.dto.tocomment.ToCommentDbDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: CommentPageMapper
 * @author: created by hang.yu on 2020/4/11 21:25
 */
public interface CommentMapper {

    List<CommentPageDbDTO> commentPage(Page page, @Param("request") CommentPageRequestDbDTO requestDTO);

    ToCommentDbDTO toComment(@Param("subOrderId") Long subOrderId, @Param("creator") Long creator);
}
