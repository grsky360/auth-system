package ilio.oauth.core.controller;

import ilio.oauth.core.controller.req.LoginParam;
import ilio.oauth.core.controller.resp.JsonResult;
import ilio.oauth.core.controller.resp.UserResp;
import ilio.oauth.core.exceptions.UnauthorizedException;
import ilio.oauth.core.model.generated.tables.records.UserRecord;
import ilio.oauth.core.service.AuthService;
import ilio.oauth.core.service.data.AccessToken;
import jodd.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthCtrl {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginParam loginParam) {
        AccessToken token = authService.login(loginParam.getUsername(), loginParam.getPassword());
        return JsonResult.of(token);
    }

    @GetMapping("/auth")
    public JsonResult auth(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) {
            throw new UnauthorizedException();
        }
        UserRecord user = authService.getUserInfo(token);
        if (user == null) {
            throw new UnauthorizedException();
        }
        UserResp resp = new UserResp();
        BeanUtils.copyProperties(user, resp);
        return JsonResult.of(resp);
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtil.isEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.substring("Bearer ".length());
    }
}
