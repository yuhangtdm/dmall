package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.MerchantsDO;
import com.dmall.bms.generator.mapper.MerchantsMapper;
import com.dmall.bms.generator.service.IMerchantsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 商家店铺表 1期只有一家店铺
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Service
public class IMerchantsServiceImpl extends ServiceImpl<MerchantsMapper, MerchantsDO> implements IMerchantsService {

}
