package com.dmall.common.model.swagger;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2020/5/16 17:14
 */
@Data
public class OperationInfo {

    private List<String> tags;
    private String summary;
}
