package com.dmall.component.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.dmall.common.constants.Constants;
import com.dmall.common.model.user.AdminUserContextHolder;
import com.dmall.common.model.user.UserDTO;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @description: feign拦截器
 * @author: created by hang.yu on 2020/1/7 22:41
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //从应用上下文中取出user信息，放入Feign的请求头中
        UserDTO userDTO = AdminUserContextHolder.get();
        if (userDTO != null) {
            String userJson = JSON.toJSONString(userDTO);
            requestTemplate.header(Constants.ADMIN_USER, userJson);
            requestTemplate.header(Constants.SOURCE, userDTO.getSource());
        }
    }
}
