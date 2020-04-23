package com.dmall.sso.service.impl.portal;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.JsonUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.util.HttpClientUtil;
import com.dmall.mms.api.dto.member.WeiBoLoginRequestDTO;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import com.dmall.sso.api.enums.ThirdPartyPlatformErrorEnum;
import com.dmall.sso.api.service.WeiBoLoginService;
import com.dmall.sso.service.impl.feign.ThirdPartyPlatformFeign;
import com.dmall.sso.service.impl.portal.handler.WeiBoLoginHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 微博服务实现
 * @author: created by hang.yu on 2020/3/1 16:59
 */
@Slf4j
@RestController
public class WeiBoLoginServiceImpl implements WeiBoLoginService {

    @Autowired
    private ThirdPartyPlatformFeign thirdPartyPlatformFeign;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private WeiBoLoginHandler weiBoLoginHandler;

    /**
     * 获取微博的ACCESS_TOKEN请求地址
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";

    /**
     * 获取微博的用户信息
     */
    private static final String WEIBO_USER_URL = "https://api.weibo.com/2/users/show.json?access_token={}&uid={}";

    @Value("${dmall.weibo.appid}")
    private String appId;

    @Value("${dmall.weibo.appsecret}")
    private String appSecret;

    @Value("${dmall.weibo.redirect_uri}")
    private String redirectUri;


    @Override
    public BaseResult<PortalLoginResponseDTO> login(String code) {
        // 获取ACCESS_TOKEN
        String result = httpClientUtil.post(ACCESS_TOKEN_URL, getParamMap(code));
        Map<String, String> accessTokenObject = JsonUtil.fromJson(result, new TypeReference<Map<String, String>>() {
        });
        if (accessTokenObject == null || StrUtil.isNotBlank(accessTokenObject.get("error_code"))) {
            log.error("获取ACCESS_TOKEN错误,{}", result);
            return ResultUtil.fail(ThirdPartyPlatformErrorEnum.WEI_BO_ERROR);
        }
        String access_token = accessTokenObject.get("access_token");
        String uid = accessTokenObject.get("uid");
        // 获取微博用户信息
        String userInfo = httpClientUtil.get(StrUtil.format(WEIBO_USER_URL, access_token, uid));
        JSONObject userInfoObject = JsonUtil.fromJson(result, JSONObject.class);

        if (userInfoObject == null || StrUtil.isNotBlank(userInfoObject.getStr("error_code"))) {
            log.error("获取微博用户信息错误,{}", userInfo);
            return ResultUtil.fail(ThirdPartyPlatformErrorEnum.WEI_BO_ERROR);
        }
        // 调用member接口注册
        BaseResult<PortalMemberDTO> weiBoLogin = thirdPartyPlatformFeign.weiBoLogin(getWeiBoLoginRequestDTO(userInfoObject));

        return weiBoLoginHandler.handler(weiBoLogin.getData());
    }

    private WeiBoLoginRequestDTO getWeiBoLoginRequestDTO(JSONObject userInfoObject) {
        WeiBoLoginRequestDTO weiBoLoginRequestDTO = new WeiBoLoginRequestDTO();
        weiBoLoginRequestDTO.setWeiBoNo(userInfoObject.getLong("id"));
        weiBoLoginRequestDTO.setName(userInfoObject.getStr("screen_name"));
        weiBoLoginRequestDTO.setNickName(userInfoObject.getStr("name"));
        weiBoLoginRequestDTO.setIcon(userInfoObject.getStr("avatar_large"));
        weiBoLoginRequestDTO.setGender(userInfoObject.getStr("gender"));
        return weiBoLoginRequestDTO;
    }

    private Map<String, String> getParamMap(String code) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("client_id", appId);
        paramMap.put("client_secret", appSecret);
        paramMap.put("grant_type", "authorization_code");
        paramMap.put("redirect_uri", redirectUri);
        paramMap.put("code", code);
        return paramMap;
    }
}
