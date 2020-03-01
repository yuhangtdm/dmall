package com.dmall.sso.service.impl.portal;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.util.HttpClientUtil;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import com.dmall.sso.api.service.WeiBoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 微博服务实现
 * @author: created by hang.yu on 2020/3/1 16:59
 */
@RestController
public class WeiBoLoginServiceImpl implements WeiBoLoginService {

    private static final String ACCESS_TOKEN =
            "https://api.weibo.com/oauth2/access_token?client_id=2748050258&client_secret=Yf9114350c4c946a22aac75c84f75b838&grant_type=authorization_code&redirect_uri=http://www.dmall.cloud/weibo/auth&code={}";

    @Autowired
    private HttpClientUtil httpClientUtil;

    private static final String WEIBO_USER_URL = "https://api.weibo.com/2/users/show.json?access_token={}&uid={}";

    @Override
    public BaseResult<PortalLoginResponseDTO> login(String code) {
        // 获取ACCESS_TOKEN
        String result = httpClientUtil.get(StrUtil.format(ACCESS_TOKEN, code));
        JSONObject jsonObject = JSONObject.parseObject(result);
        String access_token = jsonObject.getString("access_token");
        String uid = jsonObject.getString("uid");
        // 请求用户信息
        String userInfo = httpClientUtil.get(StrUtil.format(WEIBO_USER_URL, access_token, uid));
        JSONObject userInfoObj = JSONObject.parseObject(userInfo);
        System.out.println(userInfoObj.toJSONString());
        return ResultUtil.success();
    }
}
