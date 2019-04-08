package ilio.auth.core.model.dao.impl;

import ilio.auth.core.model.dao.UserDao;
import ilio.auth.core.model.generated.Tables;
import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.model.support.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public UserRecord getByUserNameAndPassword(String username, String encryptedPassword) {
        return dsl().selectFrom(Tables.USER)
                .where(Tables.USER.USERNAME.eq(username))
                .and(Tables.USER.PASSWORD.eq(encryptedPassword))
                .fetchOne();
    }

    @Override
    public UserRecord getByUserId(Long userId) {
        return dsl().selectFrom(Tables.USER)
                .where(Tables.USER.USER_ID.eq(userId))
                .fetchOne();
    }
}
