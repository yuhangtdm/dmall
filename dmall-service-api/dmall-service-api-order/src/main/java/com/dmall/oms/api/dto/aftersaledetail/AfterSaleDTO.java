package com.dmall.oms.api.dto.aftersaledetail;

import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.AfterSaleTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 售后单信息实体
 * @author: created by hang.yu on 2020/4/14 23:38
 */
@Data
@ApiModel(value = "AfterSaleDTO", description = "售后单信息实体")
public class AfterSaleDTO {

    @ApiModelProperty(value = "售后单号", position = 1)
    private Long afterSaleId;

    @ApiModelProperty(value = "订单号", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "订单项号", position = 3)
    private Long orderItemId;

    @ApiModelProperty(value = "售后类型", position = 4)
    private AfterSaleTypeEnum type;

    @ApiModelProperty(value = "售后状态", position = 5)
    private AfterSaleStatusEnum status;

    @ApiModelProperty(value = "处理人员", position = 6)
    private Long handleMan;

    @ApiModelProperty(value = "申请时间", position = 7)
    private Date applyTime;

    @ApiModelProperty(value = "处理时间", position = 8)
    private Date handleTime;

    @ApiModelProperty(value = "退款时间", position = 9)
    private Date refundTime;

    @ApiModelProperty(value = "填写快递单号时间", position = 10)
    private Date fillLogisticsNoTime;

    @ApiModelProperty(value = "卖家收货时间", position = 11)
    private Date receiveTime;

    @ApiModelProperty(value = "拒绝时间", position = 12)
    private Date refuseTime;

    @ApiModelProperty(value = "关闭时间", position = 13)
    private Date closeTime;

    @ApiModelProperty(value = "物流单号", position = 14)
    private String logisticsNo;

    @ApiModelProperty(value = "skuId", position = 15)
    private Long skuId;

    @ApiModelProperty(value = "申请原因", position = 16)
    private String reason;

    @ApiModelProperty(value = "申请描述", position = 17)
    private String description;

    @ApiModelProperty(value = " 申请凭证 上传凭证照片,多张以逗号隔开", position = 18)
    private String applyCredentials;

    @ApiModelProperty(value = " 处理备注", position = 18)
    private String handleNote;

    @ApiModelProperty(value = "买家发货人姓名", position = 18)
    private String buyerName;

    @ApiModelProperty(value = "买家发货人电话", position = 18)
    private String buyerPhone;

    @ApiModelProperty(value = " 买家发货人详细地址", position = 18)
    private String buyerDetailAddress;

    @ApiModelProperty(value = "卖家收货人名称", position = 18)
    private String sellerName;

    @ApiModelProperty(value = " 卖家收货人电话", position = 18)
    private String sellerPhone;

    @ApiModelProperty(value = "卖家收货人详细地址", position = 18)
    private String sellerDetailAddress;
}
