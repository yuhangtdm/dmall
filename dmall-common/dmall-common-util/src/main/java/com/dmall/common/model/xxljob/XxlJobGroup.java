package com.dmall.common.model.xxljob;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: XxlJobGroup
 * @author: created by hang.yu on 2020/04/18 14:21
 */
@Data
public class XxlJobGroup {

    private int id;

    /**
     * appname
     */
    private String appname;

    /**
     * title
     */
    private String title;

    /**
     * 执行器地址类型：0=自动注册、1=手动录入
     */
    private int addressType;

    /**
     * 执行器地址列表，多地址逗号分隔(手动录入)
     */
    private String addressList;

    // registry list
    private List<String> registryList; // 执行器地址列表(系统注册)

    public List<String> getRegistryList() {
        if (addressList != null && addressList.trim().length() > 0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

}
