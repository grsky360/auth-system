package ilio.oauth.core.controller;

import ilio.oauth.core.config.properties.FacebookProperties;
import ilio.oauth.core.controller.resp.JsonResult;
import ilio.oauth.core.service.FacebookOAuthService;
import ilio.oauth.core.service.WeiboOAuthService;
import ilio.oauth.core.service.data.facebook.FacebookAccessToken;
import ilio.oauth.core.service.data.weibo.WeiboAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/callback")
public class OAuthCallbackCtrl {

    @Autowired
    private WeiboOAuthService weiboOAuthService;

    @Autowired
    private FacebookOAuthService facebookOAuthService;

    @GetMapping("/weibo")
    public JsonResult weiboCallback(String code) {
        WeiboAccessToken accessToken = weiboOAuthService.getAccessToken(code);
        return JsonResult.of(weiboOAuthService.getUserInfo(accessToken.getAccessToken(), accessToken.getUid()));
    }

    @GetMapping("/weibo/cancel")
    public JsonResult weiboCancelCallback(String source, String uid, String auth_end) {
        return JsonResult.of(Arrays.asList(source, uid, auth_end));
    }

    @GetMapping("/qq")
    public JsonResult qqCallback() {
        return JsonResult.empty();
    }

    @GetMapping("/facebook")
    public JsonResult fbCallback(String code) {
        FacebookAccessToken accessToken = facebookOAuthService.getAccessToken(code);
        return JsonResult.of(facebookOAuthService.checkAccessToken(accessToken.getAccessToken()));
    }
}
