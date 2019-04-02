package ilio.auth;

import ilio.auth.model.generated.tables.User;
import ilio.auth.model.generated.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    @Autowired
    private DSLContext create;

    @Test
    public void test() {
        UserRecord userRecord = create.newRecord(User.USER);
        userRecord.with(User.USER.USERNAME, "abc")
                .with(User.USER.ID, 1);

        int i = userRecord.insert();
        System.out.println(i);
    }

}
