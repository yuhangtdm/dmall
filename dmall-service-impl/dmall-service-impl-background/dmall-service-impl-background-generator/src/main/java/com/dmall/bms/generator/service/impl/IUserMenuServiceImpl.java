package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.UserMenuDO;
import com.dmall.bms.generator.mapper.UserMenuMapper;
import com.dmall.bms.generator.service.IUserMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 用户-菜单表 
 * @author: created by hang.yu on 2020-02-20 21:36:44
 */
@Service
public class IUserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenuDO> implements IUserMenuService {

}
