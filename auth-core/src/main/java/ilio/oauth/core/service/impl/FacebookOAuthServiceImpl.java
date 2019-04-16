package ilio.oauth.core.service.impl;

import com.alibaba.fastjson.JSON;
import ilio.oauth.core.config.properties.FacebookProperties;
import ilio.oauth.core.service.FacebookOAuthService;
import ilio.oauth.core.service.data.facebook.FacebookAccessToken;
import ilio.oauth.core.service.data.facebook.FacebookDebugToken;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FacebookOAuthServiceImpl implements FacebookOAuthService {

    @Autowired
    private FacebookProperties fbProperties;

    private static final String fbApi = "https://graph.facebook.com";
    private static final String fbApiVersion = "/v3.2";
    private static final String accessTokenUri = "/oauth/access_token";
    private static final String debugTokenUri = "/debug_token";

    private FacebookAccessToken appAccessToken;
    @PostConstruct
    public void init() {
        HttpResponse response = request("get", accessTokenUri)
                .query("client_id", fbProperties.getAppId(),
                        "client_secret", fbProperties.getAppSecret(),
                        "grant_type", "client_credentials")
                .send();
        String responseBody = response.body();
        appAccessToken = JSON.parseObject(responseBody, FacebookAccessToken.class);
    }

    @Override
    public FacebookAccessToken getAccessToken(String code) {
        HttpResponse response = request("get", accessTokenUri)
                .query("client_id", fbProperties.getAppId(),
                        "client_secret", fbProperties.getAppSecret(),
                        "redirect_uri", fbProperties.getCallbackUrl(),
                        "code", code)
                .send();
        String responseBody = response.body();
        FacebookAccessToken token = JSON.parseObject(responseBody, FacebookAccessToken.class);
        return token;
    }

    @Override
    public FacebookDebugToken checkAccessToken(String accessToken) {
        HttpResponse response = request("get", debugTokenUri)
                .query("access_token", appAccessToken.getAccessToken(),
                        "input_token", accessToken)
                .send();
        String responseBody = response.body();
        FacebookDebugToken token = JSON.parseObject(responseBody).getObject("data", FacebookDebugToken.class);
        return token;
    }

    private HttpRequest request(String method, String uri) {
        return HttpRequest.create(method, fbApi + fbApiVersion + uri);
    }
}
