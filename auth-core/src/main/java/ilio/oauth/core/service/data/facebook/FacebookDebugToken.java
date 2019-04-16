package ilio.oauth.core.service.data.facebook;

import lombok.Data;

import java.util.List;

@Data
public class FacebookDebugToken {
    private Boolean isValid;
    private Long userId;
    private List<String> scopes;
}
