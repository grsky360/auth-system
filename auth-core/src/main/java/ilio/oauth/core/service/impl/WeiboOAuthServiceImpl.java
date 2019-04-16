package ilio.oauth.core.service.impl;

import com.alibaba.fastjson.JSON;
import ilio.oauth.core.config.properties.WeiboProperties;
import ilio.oauth.core.service.WeiboOAuthService;
import ilio.oauth.core.service.data.weibo.WeiboAccessToken;
import ilio.oauth.core.service.data.weibo.WeiboUser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeiboOAuthServiceImpl implements WeiboOAuthService {

    @Autowired
    private WeiboProperties weiboProperties;

    private static final String weiboApi = "https://api.weibo.com";
    private static final String accessTokenUri = "/oauth2/access_token";
    private static final String showUserUri = "/2/users/show.json";

    public WeiboAccessToken getAccessToken(String code) {
        HttpResponse response = request("post", accessTokenUri)
                .form("grant_type", "authorization_code",
                        "redirect_uri", weiboProperties.getCallbackUri(),
                        "code", code)
                .send();
        String responseBody = response.body();
        return JSON.parseObject(responseBody, WeiboAccessToken.class);
    }

    @Override
    public WeiboUser getUserInfo(String accessToken, Long uid) {
        HttpResponse response = request("get", showUserUri)
                .form("access_token", accessToken,
                        "uid", uid)
                .send();
        String responseBody = response.body();
        return JSON.parseObject(responseBody, WeiboUser.class);
    }

    private HttpRequest request(String method, String uri) {
        return HttpRequest.create(method, weiboApi + uri)
                .header("client_id", weiboProperties.getAppId())
                .header("client_secret", weiboProperties.getAppSecret())
                .query("source", weiboProperties.getAppId());
    }
}
