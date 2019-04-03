package ilio.auth.core.controller.resp;

import lombok.Data;

@Data
public class AccessTokenResp {
    private String token;
    private Long expiresAt;
}
