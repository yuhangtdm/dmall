package com.dmall.web.admin.vo.select;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: SelectVO
 * @author: created by hang.yu on 2020/5/10 13:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectVO {

    private String selectName;

    private List<SelectItemVO> items;
}
