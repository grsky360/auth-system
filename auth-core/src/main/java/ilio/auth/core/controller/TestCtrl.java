package ilio.auth.core.controller;

import ilio.auth.core.controller.resp.JsonResult;
import ilio.auth.support.AuthRequired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCtrl {

    @AuthRequired
    @GetMapping("/pong")
    public JsonResult ping() {
        return JsonResult.of("pong");
    }
}
