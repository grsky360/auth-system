package ilio.auth.core;

import com.alibaba.fastjson.JSON;
import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.service.AuthService;
import ilio.auth.support.AuthConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthCoreApplication.class)
public class AuthCoreApplicationTests {

    @Autowired
    private AuthService authService;

    @Test
    public void test1() {
        UserRecord user = authService.getUserInfo("f_a4a9ee0c75d574fecc66bd2aeb3bda3db0489fd5");
        System.out.println(JSON.toJSONString(user));
    }

}
