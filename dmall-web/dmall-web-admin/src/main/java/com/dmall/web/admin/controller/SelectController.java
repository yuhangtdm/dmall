package com.dmall.web.admin.controller;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.base.CodeDescEnum;
import com.dmall.common.util.ResultUtil;
import com.dmall.web.admin.vo.select.SelectItemVO;
import com.dmall.web.admin.vo.select.SelectVO;
import com.google.common.collect.Lists;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @description: 下拉框控制器
 * @author: created by hang.yu on 2020/5/10 11:00
 */
@RestController
public class SelectController {

    private static final List<String> enumPackages;

    static {
        enumPackages = Lists.newArrayList();
        enumPackages.add("com.dmall.bms.api.enums");
        enumPackages.add("com.dmall.cart.api.enums");
        enumPackages.add("com.dmall.mms.api.enums");
        enumPackages.add("com.dmall.oms.api.enums");
        enumPackages.add("com.dmall.pay.api.enums");
        enumPackages.add("com.dmall.pms.api.enums");
        enumPackages.add("com.dmall.sso.api.enums");
    }

    @RequestMapping("/select")
    public BaseResult select() {
        return ResultUtil.success(buildSelect());
    }

    public List<SelectVO> buildSelect() {
        List<SelectVO> list = Lists.newArrayList();
        for (String enumPackage : enumPackages) {
            Reflections reflections = new Reflections(enumPackage);
            Set<Class<? extends CodeDescEnum>> monitorClasses = reflections.getSubTypesOf(CodeDescEnum.class);
            for (Class<? extends CodeDescEnum> monitorClass : monitorClasses) {
                if (monitorClass != null) {
                    SelectVO selectVO = new SelectVO();
                    selectVO.setSelectName(monitorClass.getSimpleName());
                    selectVO.setItems(Lists.newArrayList());
                    CodeDescEnum[] enumConstants = monitorClass.getEnumConstants();
                    if (enumConstants != null) {
                        for (CodeDescEnum anEnum : enumConstants) {
                            SelectItemVO selectItemVO = new SelectItemVO(anEnum.getCode(), anEnum.getDesc());
                            selectVO.getItems().add(selectItemVO);
                        }
                    }
                    list.add(selectVO);
                }
            }
        }
        return list;
    }

}
