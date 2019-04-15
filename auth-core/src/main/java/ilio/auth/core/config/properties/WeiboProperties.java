package ilio.auth.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oauth.weibo")
public class WeiboProperties {
    private String appId;
    private String appSecret;
    private String callbackUri;
    private String cancelCallbackUri;
}
