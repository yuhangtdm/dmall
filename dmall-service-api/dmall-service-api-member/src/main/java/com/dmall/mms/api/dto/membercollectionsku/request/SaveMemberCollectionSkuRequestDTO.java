package com.dmall.mms.api.dto.membercollectionsku.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.membercollectionsku.common.CommonMemberCollectionSkuRequestDTO;

/**
 * @description: 新增会员收藏sku请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveMemberCollectionSkuRequestDTO", description="新增会员收藏sku请求实体")
public class SaveMemberCollectionSkuRequestDTO extends CommonMemberCollectionSkuRequestDTO {

}
