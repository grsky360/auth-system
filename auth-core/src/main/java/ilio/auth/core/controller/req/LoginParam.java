package ilio.auth.core.controller.req;

import lombok.Data;

@Data
public class LoginParam {
    private String username;
    private String password;
}
