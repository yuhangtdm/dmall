package com.dmall.pms.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.generator.service.IAttributeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author hang.yu
 * @since 2019-11-24
 */
@Service
public class IAttributeServiceImpl extends ServiceImpl<AttributeMapper, AttributeDO> implements IAttributeService {

}
