package ilio.auth.core.controller;

import ilio.auth.core.controller.resp.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/callback")
public class OAuthCallbackCtrl {

    @GetMapping("/weibo")
    public JsonResult weiboCallback(String code) {
        return JsonResult.of(code);
    }

    @GetMapping("/weibo/cancel")
    public JsonResult weiboCancelCallback(String source, String uid, String auth_end) {
        return JsonResult.of(Arrays.asList(source, uid, auth_end));
    }

    @GetMapping("/qq")
    public JsonResult qqCallback() {
        return JsonResult.ok();
    }
}
