package ilio.auth.core.service.impl;

import ilio.auth.core.exceptions.CommonException;
import ilio.auth.core.model.dao.AccessTokenDao;
import ilio.auth.core.model.dao.UserDao;
import ilio.auth.core.model.generated.tables.records.AccessTokenRecord;
import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.service.AuthService;
import lombok.var;
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
    private AccessTokenDao accessTokenDao;

    @Override
    public AccessTokenRecord login(String username, String password) {
        var encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        var userRecord = userDao.getByUserNameAndPassword(username, encryptedPassword);
        if (userRecord == null) {
            throw new CommonException("wrong username or password");
        }
        var record = accessTokenDao.getByUserId(userRecord.getUserId());
        if (record != null) {
            if (isValid(record.getToken())) {
                renewToken(record.getToken());
                return record;
            }
        }
        record = new AccessTokenRecord();
        record.setUserId(userRecord.getUserId());
        record.setToken(generateTokenStr());
        record.setExpiresAt(LocalDateTime.now().plusHours(2));
        record.insert();
        return record;
    }

    @Override
    public UserRecord getUserInfo(String accessToken) {
        if (!isValid(accessToken)) {
            return null;
        }
        var record = accessTokenDao.getByToken(accessToken);
        return userDao.getByUserId(record.getUserId());
    }

    @Override
    public boolean isValid(String accessToken) {
        var record = accessTokenDao.getByToken(accessToken);
        if (record != null) {
            if (record.getExpiresAt().isBefore(LocalDateTime.now())) {
                record.delete();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean renewToken(String accessToken) {
        if (!isValid(accessToken)) {
            return false;
        }
        var record = accessTokenDao.getByToken(accessToken);
        record.setExpiresAt(LocalDateTime.now().plusHours(2));
        record.update();
        return true;
    }

    private String generateTokenStr() {
        var accessToken = UUID.randomUUID().toString().replaceAll("-", "");
        accessToken += "$-+-#";
        var chars = accessToken.toCharArray();
        var random = new Random();
        for (var i = 1; i < chars.length; i++) {
            var j = random.nextInt(i);
            var tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}
