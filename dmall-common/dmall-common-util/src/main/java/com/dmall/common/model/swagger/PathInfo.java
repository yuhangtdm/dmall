package com.dmall.common.model.swagger;

import lombok.Data;

/**
 * @description:
 * @author: created by hang.yu on 2020/5/16 17:12
 */
@Data
public class PathInfo {

    private OperationInfo get;
    private OperationInfo put;
    private OperationInfo post;
    private OperationInfo head;
    private OperationInfo delete;
    private OperationInfo patch;
    private OperationInfo options;

}
