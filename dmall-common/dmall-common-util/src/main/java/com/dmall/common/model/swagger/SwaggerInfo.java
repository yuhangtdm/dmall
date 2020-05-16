package com.dmall.common.model.swagger;

import io.swagger.models.Info;
import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @author: created by hang.yu on 2020/5/16 17:06
 */
@Data
public class SwaggerInfo {
    protected String swagger = "2.0";
    protected Info info;
    protected String host;
    protected String basePath;
    protected Map<String, PathInfo> paths;
}
