package com.dmall.bms.api.dto.deliverwarehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增商家发货仓库请求实体
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Data
@ApiModel(value = "SaveDeliverWarehouseRequestDTO", description = "新增商家发货仓库请求实体")
public class SaveDeliverWarehouseRequestDTO implements Serializable {

    @ApiModelProperty(value = "商家店铺id", position = 1)
    @NotNull(message = "商家店铺id不能为空")
    private Long merchantsId;

    @ApiModelProperty(value = "仓库名称", position = 2)
    @NotBlank(message = "仓库名称不能为空")
    private String name;

    @ApiModelProperty(value = "仓库所在省/直辖市", position = 3)
    @NotBlank(message = "仓库所在省/直辖市不能为空")
    private String province;

    @ApiModelProperty(value = "仓库所在市", position = 4)
    @NotBlank(message = "仓库所在市不能为空")
    private String city;

    @ApiModelProperty(value = "仓库所在区", position = 5)
    @NotBlank(message = "仓库所在区不能为空")
    private String region;

    @ApiModelProperty(value = "仓库详细地址", position = 6)
    @NotBlank(message = "仓库详细地址不能为空")
    private String detailAddress;

    @ApiModelProperty(value = "仓库联系人姓名", position = 7)
    private String contactName;

    @ApiModelProperty(value = "仓库联系人电话", position = 8)
    private String contactTel;

    @ApiModelProperty(value = "是否默认发货地址 Y-是,N-否", position = 9)
    private String deliverAddress;

    @ApiModelProperty(value = "默认收货地址 Y-是,N-否", position = 10)
    private String receiveAddress;
}
