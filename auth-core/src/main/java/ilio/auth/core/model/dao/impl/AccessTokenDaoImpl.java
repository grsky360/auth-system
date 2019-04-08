package ilio.auth.core.model.dao.impl;

import ilio.auth.core.model.dao.AccessTokenDao;
import ilio.auth.core.model.generated.Tables;
import ilio.auth.core.model.generated.tables.records.AccessTokenRecord;
import ilio.auth.core.model.support.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenDaoImpl extends BaseDao implements AccessTokenDao {

    @Override
    public AccessTokenRecord getByUserId(Long userId) {
        return dsl().selectFrom(Tables.ACCESS_TOKEN)
                .where(Tables.ACCESS_TOKEN.USER_ID.eq(userId))
                .fetchOne();
    }

    @Override
    public AccessTokenRecord getByToken(String accessToken) {
        return dsl().selectFrom(Tables.ACCESS_TOKEN)
                .where(Tables.ACCESS_TOKEN.TOKEN.eq(accessToken))
                .fetchOne();
    }
}
