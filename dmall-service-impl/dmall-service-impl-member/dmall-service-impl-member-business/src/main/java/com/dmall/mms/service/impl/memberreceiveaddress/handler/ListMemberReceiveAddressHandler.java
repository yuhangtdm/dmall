package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberreceiveaddress.response.ReceiveAddressResponseDTO;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 会员收货地址列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class ListMemberReceiveAddressHandler extends AbstractCommonHandler<Void, MemberReceiveAddressDO, ReceiveAddressResponseDTO> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<List<ReceiveAddressResponseDTO>> processor(Void aVoid) {
        // 查询地址列表
        List<MemberReceiveAddressDO> list = memberReceiveAddressMapper.selectList(Wrappers.<MemberReceiveAddressDO>lambdaQuery()
                .orderByDesc(MemberReceiveAddressDO::getModifier));

        if (CollUtil.isEmpty(list)){
            return ResultUtil.success(Lists.newArrayList());
        }
        // 得到默认地址的索引
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            MemberReceiveAddressDO addressDO = list.get(i);
            if (YNEnum.Y.getCode().equals(addressDO.getDefaultStatus())) {
                index = i;
            }
        }
        // 将默认地址交换到第一个
        if (index != -1 && index != 0){
            Collections.swap(list, 0, index);
        }
        List<ReceiveAddressResponseDTO> collect = list.stream()
                .map(addressDO -> doConvertDto(addressDO, ReceiveAddressResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }

}
