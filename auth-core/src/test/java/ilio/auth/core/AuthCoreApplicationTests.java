package ilio.auth.core;

import com.alibaba.fastjson.JSON;
import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.service.AuthService;
import ilio.auth.support.AuthConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthCoreApplication.class)
public class AuthCoreApplicationTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test1() {
        redisTemplate.opsForHash().put("a", "b", "c");
    }

}
