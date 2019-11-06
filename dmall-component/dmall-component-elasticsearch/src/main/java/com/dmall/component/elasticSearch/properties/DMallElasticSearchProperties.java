package com.dmall.component.elasticSearch.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: created by yuhang on 2019/11/6 22:59
 */
@ConfigurationProperties(prefix = "dmall.elasticsearch")
@Data
public class DMallElasticSearchProperties {

    /**
     * 是否启用es
     */
    private Boolean enabled = Boolean.TRUE;

    /**
     * 默认索引名称
     */
    private String indexName;

    /**
     * 默认类型名称
     */
    private String typeName;


}
