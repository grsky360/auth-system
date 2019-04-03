package ilio.auth.core.service.impl;

import ilio.auth.core.exceptions.CommonException;
import ilio.auth.core.model.generated.Tables;
import ilio.auth.core.model.generated.tables.records.AccessTokenRecord;
import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.service.AuthService;
import lombok.var;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private DSLContext dsl;

    @Override
    public AccessTokenRecord login(String username, String password) {
        var encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        var userRecord = dsl.selectFrom(Tables.USER)
                .where(Tables.USER.USERNAME.eq(username))
                .and(Tables.USER.PASSWORD.eq(encryptedPassword))
                .fetchOne();
        if (userRecord == null) {
            throw new CommonException("wrong username or password");
        }
        var record = dsl.selectFrom(Tables.ACCESS_TOKEN)
                .where(Tables.ACCESS_TOKEN.USER_ID.eq(userRecord.getUserId()))
                .fetchOne();
        if (record != null) {
            if (isValid(record.getToken())) {
                renewToken(record.getToken());
                return record;
            }
        }
        record = dsl.newRecord(Tables.ACCESS_TOKEN);
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
        var record = dsl.selectFrom(Tables.ACCESS_TOKEN)
                .where(Tables.ACCESS_TOKEN.TOKEN.eq(accessToken))
                .fetchOne();
        return dsl.selectFrom(Tables.USER)
                .where(Tables.USER.USER_ID.eq(record.getUserId()))
                .fetchOne();
    }

    @Override
    public boolean isValid(String accessToken) {
        var record = dsl.selectFrom(Tables.ACCESS_TOKEN)
                .where(Tables.ACCESS_TOKEN.TOKEN.eq(accessToken))
                .fetchOne();
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
        var record = dsl.selectFrom(Tables.ACCESS_TOKEN)
                .where(Tables.ACCESS_TOKEN.TOKEN.eq(accessToken))
                .fetchOne();
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
