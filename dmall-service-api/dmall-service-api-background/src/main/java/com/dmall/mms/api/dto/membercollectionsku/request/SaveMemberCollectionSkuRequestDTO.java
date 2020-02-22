package com.dmall.mms.api.dto.membercollectionsku.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.membercollectionsku.common.CommonMemberCollectionSkuRequestDTO;

/**
 * @description: 新增会员收藏sku请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMemberCollectionSkuRequestDTO", description = "新增会员收藏sku请求实体")
public class SaveMemberCollectionSkuRequestDTO extends CommonMemberCollectionSkuRequestDTO {

}
