package ilio.auth.core.service.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccessToken {
    private Long userId;
    private String token;
    private LocalDateTime expiresAt;
}
