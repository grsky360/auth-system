package ilio.auth.core.controller;

import ilio.auth.core.controller.req.LoginParam;
import ilio.auth.core.controller.resp.JsonResult;
import ilio.auth.core.controller.resp.UserResp;
import ilio.auth.core.exceptions.UnauthorizedException;
import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.service.AuthService;
import ilio.auth.core.service.data.AccessToken;
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
