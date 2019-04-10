package ilio.auth.core.controller;

import ilio.auth.core.controller.resp.JsonResult;
import ilio.auth.support.AuthRequired;
import ilio.auth.support.AuthUser;
import ilio.auth.support.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestCtrl {

    @AuthRequired
    @GetMapping("/ping")
    public JsonResult ping(@AuthUser User user) {
        log.info("{}", user);
        return JsonResult.of("pong");
    }
}
