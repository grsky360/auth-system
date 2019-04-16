package ilio.oauth.core.service.data.weibo;

import lombok.Data;

@Data
public class WeiboAccessToken {
    private String accessToken;
    private Long expiresIn;
    private Long uid;
//    private Boolean isRealName;
}
