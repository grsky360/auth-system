package ilio.oauth.core.service.impl;

import ilio.oauth.core.exceptions.CommonException;
import ilio.oauth.core.model.generated.Tables;
import ilio.oauth.core.model.generated.tables.records.UserRecord;
import ilio.oauth.core.service.AuthService;
import ilio.oauth.core.service.data.AccessToken;
import ilio.oauth.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public AccessToken login(String username, String password) {
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        UserRecord userRecord = new UserRecord().with(Tables.USER.USER_ID, 1L);
        if (userRecord == null) {
            throw new CommonException("wrong username or password");
        }
        AccessToken accessToken = redisUtil.getMapCache("user_token", userRecord.getUserId());
        if (accessToken != null) {
            if (isValid(accessToken)) {
                renewToken(accessToken);
                return accessToken;
            }
        }
        accessToken = generateToken(userRecord.getUserId());
        redisUtil.putMapCache("user_token", userRecord.getUserId(), accessToken, 2 * 3600);
        redisUtil.putMapCache("token", accessToken.getToken(), accessToken, 2 * 3600);
        return accessToken;
    }

    @Override
    public UserRecord getUserInfo(String token) {
        AccessToken accessToken = redisUtil.getMapCache("token", token);
        if (accessToken == null || !isValid(accessToken)) {
            return null;
        }
        renewToken(accessToken);
        return new UserRecord().with(Tables.USER.USER_ID, accessToken.getUserId());
    }

    private boolean isValid(AccessToken accessToken) {
        if (accessToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            redisUtil.removeMapCache("user_token", accessToken.getUserId());
            redisUtil.removeMapCache("token", accessToken.getToken());
            return false;
        }
        return true;
    }

    private void renewToken(AccessToken accessToken) {
        if (!isValid(accessToken)) {
            return;
        }
        accessToken.setExpiresAt(LocalDateTime.now().plusHours(2));
        redisUtil.putMapCache("user_token", accessToken.getUserId(), accessToken, 2 * 3600);
        redisUtil. putMapCache("token", accessToken.getToken(), accessToken, 2 * 3600);
    }

    private AccessToken generateToken(Long userId) {
        AccessToken accessToken = new AccessToken();
        accessToken.setUserId(userId);
        accessToken.setToken(generateTokenStr());
        accessToken.setExpiresAt(LocalDateTime.now().plusHours(2));
        return accessToken;
    }

    private String generateTokenStr() {
        String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
        accessToken += "$-+-#";
        char[] chars = accessToken.toCharArray();
        Random random = new Random();
        for (int i = 1; i < chars.length; i++) {
            int j = random.nextInt(i);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}
