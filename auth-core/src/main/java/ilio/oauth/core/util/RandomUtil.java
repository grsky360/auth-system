package ilio.oauth.core.util;

import org.springframework.util.DigestUtils;

import java.util.UUID;

public class RandomUtil {

    public static String random32() {
        String uuid = UUID.randomUUID().toString();
        return DigestUtils.md5DigestAsHex(uuid.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(random32());
    }
}
