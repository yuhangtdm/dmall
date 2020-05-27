package com.dmall.pms.api.dto.category.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: CategoryTreeDTO
 * @author: created by hang.yu on 2020/5/24 17:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTreeDTO {
    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 类型
     */
    private Integer type;
}
