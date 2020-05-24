package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.bms.generator.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 菜单表
 * @author: created by hang.yu on 2020-02-20 21:36:44
 */
@Service
public class IMenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements IMenuService {

}
