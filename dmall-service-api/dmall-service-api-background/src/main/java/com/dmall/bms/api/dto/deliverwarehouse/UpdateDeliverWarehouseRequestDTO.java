package com.dmall.bms.api.dto.deliverwarehouse;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.YNEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改商家发货仓库请求实体
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Data
@ApiModel(value = "UpdateDeliverWarehouseRequestDTO", description = "修改商家发货仓库请求实体")
public class UpdateDeliverWarehouseRequestDTO implements Serializable {

    @ApiModelProperty(value = "仓库id", required = true, position = 1)
    @NotNull(message = "仓库id不能为空")
    private Long id;

    @ApiModelProperty(value = "仓库名称", position = 2)
    private String name;

    @ApiModelProperty(value = "仓库详细地址", position = 3)
    private String detailAddress;

    @ApiModelProperty(value = "仓库联系人姓名", position = 4)
    private String contactName;

    @ApiModelProperty(value = "仓库联系人电话", position = 5)
    private String contactTel;

    @ApiModelProperty(value = "默认发货地址 Y-是,N-否", position = 6)
    @ValueInEnum(YNEnum.class)
    private String deliverAddress;

    @ApiModelProperty(value = "默认收货地址 Y-是,N-否", position = 7)
    @ValueInEnum(YNEnum.class)
    private String receiveAddress;
}
