/*
package com.zjl.washer.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.Random;

@Component
@Slf4j
@Data
public class WeChatUtil {

    public static String getRandomStringByLength(int length){
        String base = "ABCDEFGHIJKMLNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i< length;i++){
            int number = random.nextInt(base.length());
            stringBuffer.append(base.charAt(number));
        }
        return stringBuffer.toString();
    }

    public final static String SCOPE_BASE ="snsapi_base";
    public final static String SCOPE_USERINFO = "snsapi_userinfo";

    @SuppressWarnings("unused")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccessToken{
        public String access_token;
        public Integer expires_in;
        public String refresh_toekn;
        public String openid;
        public String scope;
        public String errcode;
        public String errmsg;
    }


    @SuppressWarnings("unused")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UnfiedOrderResponse{
        public String return_code;
        public String return_msg;
        public String appid;
        public String mch_id;
        public String device_info;
        public String nonce_str;
        public String sign;
        public String result_code;
        public String err_code;
        public String err_code_des;
        public String trade_type;
        public String prepay_id;
        public String code_url;
    }


    @SuppressWarnings("unused")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserInfo{
        private String openid;
        private String nickname;
        private Integer sex;
        private String language;
        private String province;
        private String city;
        private String country;
        private String headimgurl;
        private String unionid;
        private String errcode;
        private String errmsg;
    }

    public static String getCodeUrl(String getCodeUrl,String Scope,String CODEURL,String APPID) throws Exception {
        String encodeUrl = URLEncoder.encode(getCodeUrl,"utf-8");
        String path = CODEURL.replace()
    }

}
*/
