package com.dmall.component.web.exceptionhandler;

import com.dmall.common.constants.WebConstants;
import com.dmall.common.model.result.BaseResult;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 定制返回值
 * @author: created by hang.yu on 2019/11/7 23:09
 */
@Component
public class BasicErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        if (webRequest.getAttribute(WebConstants.DATA, RequestAttributes.SCOPE_REQUEST) != null) {
            BaseResult baseResult = (BaseResult) webRequest.getAttribute(WebConstants.DATA, RequestAttributes.SCOPE_REQUEST);
            errorAttributes.put(WebConstants.RESULT, baseResult.getResult());
            errorAttributes.put(WebConstants.CODE, baseResult.getCode());
            errorAttributes.put(WebConstants.MSG, baseResult.getMsg());
            if (baseResult.getData() != null) {
                errorAttributes.put(WebConstants.DATA, baseResult.getData());
            }
        } else {
            errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        }
        return errorAttributes;
    }

}
