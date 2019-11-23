package com.dmall.pms.mock.api.dto.product.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 商品请求实体
 * @author: created by hang.yu on 2019/10/14 21:51
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProductRequestDTO implements Serializable {

    private static final long serialVersionUID = 8116026063348246687L;

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String name;
}
