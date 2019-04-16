package ilio.oauth.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oauth.facebook")
public class FacebookProperties {
    private String appId;
    private String appSecret;
    private String callbackUrl;
    private String corsCheckParam = "{}";
}
