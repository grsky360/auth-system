package ilio.oauth.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oauth.qq")
public class QQProperties {
    private String appId;
    private String appKey;
    private String redirectUri;
}
