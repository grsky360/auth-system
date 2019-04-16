package ilio.oauth.core.service.data.facebook;

import lombok.Data;

@Data
public class FacebookAccessToken {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
}
