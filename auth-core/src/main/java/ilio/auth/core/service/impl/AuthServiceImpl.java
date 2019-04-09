package ilio.auth.core.service.impl;

import ilio.auth.core.exceptions.CommonException;
import ilio.auth.core.model.dao.UserDao;
import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.service.AuthService;
import ilio.auth.core.service.data.AccessToken;
import ilio.auth.core.util.RedisUtil;
import org.redisson.api.RMapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public AccessToken login(String username, String password) {
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        UserRecord userRecord = userDao.getByUserNameAndPassword(username, encryptedPassword);
        if (userRecord == null) {
            throw new CommonException("wrong username or password");
        }
        AccessToken accessToken = getMapCache("user_token", userRecord.getUserId());
        if (accessToken != null) {
            if (isValid(accessToken)) {
                renewToken(accessToken);
                return accessToken;
            }
        }
        accessToken = generateToken(userRecord.getUserId());
        putMapCache("user_token", userRecord.getUserId(), accessToken);
        putMapCache("token", accessToken.getToken(), accessToken);
        return accessToken;
    }

    @Override
    public UserRecord getUserInfo(String token) {
        AccessToken accessToken = getMapCache("token", token);
        if (accessToken == null || !isValid(accessToken)) {
            return null;
        }
        renewToken(accessToken);
        return userDao.getByUserId(accessToken.getUserId());
    }

    private boolean isValid(AccessToken accessToken) {
        if (accessToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            getMapCache("token").remove(accessToken.getUserId());
            return false;
        }
        return true;
    }

    private void renewToken(AccessToken accessToken) {
        if (!isValid(accessToken)) {
            return;
        }
        accessToken.setExpiresAt(LocalDateTime.now().plusHours(2));
        putMapCache("user_token", accessToken.getUserId(), accessToken);
        putMapCache("token", accessToken.getToken(), accessToken);
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

    private <K, V> RMapCache<K, V> getMapCache(String key) {
        return redisUtil.mapCache(key);
    }

    private <K, V> V getMapCache(String key, K valueKey) {
        return this.<K, V>getMapCache(key).get(valueKey);
    }

    private <K, V> void putMapCache(String key, K valueKey, V valueValue) {
        this.<K, V>getMapCache(key).put(valueKey, valueValue);
    }
}
