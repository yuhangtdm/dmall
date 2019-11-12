package com.dmall.service.member.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dmall.BaseTest;
import com.dmall.mms.generator.dataobject.BankCardDO;
import com.dmall.mms.generator.mapper.BankCardMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @author: created by yuhang on 2019/11/2 16:39
 */

public class BankCardMapperTest extends BaseTest {

    @Autowired
    private BankCardMapper bankCardMapper;


    @Test
    public void testSelect(){
        List<BankCardDO> bankCardDOList = bankCardMapper.selectList(new QueryWrapper<>());
        bankCardDOList.forEach(bankCardDO -> {
            System.err.println(bankCardDO);
        });
    }


}
