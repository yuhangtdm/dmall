package com.dmall.pms.service.impl.attribute.validate;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.enums.base.YNEnum;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;

import java.util.HashSet;
import java.util.List;

/**
 * @description: AttributeValidate
 * @author: created by hang.yu on 2019/12/31 12:21
 */
public class AttributeValidate {

    /**
     * 新增或更新的公共校验
     */
    public static BaseResult validate(Integer inputType, List<String> inputList, String handAddStatus){
        // 从列表获取 不支持新增 可选值为空
        if (inputType.equals(InputTypeEnum.LIST.getCode()) && CollUtil.isEmpty(inputList)
                && YNEnum.N.getCode().equals(handAddStatus)) {
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_DATA_INVALID);
        }
        // 可选值列表不能重复
        if (CollUtil.isNotEmpty(inputList)){
            HashSet<String> strings = new HashSet<>(inputList);
            if (strings.size() != inputList.size()){
                return ResultUtil.fail(AttributeErrorEnum.INPUT_LIST_INVALID);
            }
        }
        return ResultUtil.success();
    }
}
