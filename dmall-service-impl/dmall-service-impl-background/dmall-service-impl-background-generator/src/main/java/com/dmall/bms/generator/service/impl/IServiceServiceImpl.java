package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.ServiceDO;
import com.dmall.bms.generator.mapper.ServiceMapper;
import com.dmall.bms.generator.service.IServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 服务表 
 * @author: created by hang.yu on 2020-02-20 21:36:44
 */
@Service
public class IServiceServiceImpl extends ServiceImpl<ServiceMapper, ServiceDO> implements IServiceService {

}
