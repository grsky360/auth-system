package ilio.auth.core.model.dao;

import ilio.auth.core.model.generated.tables.records.AccessTokenRecord;

public interface AccessTokenDao {

    AccessTokenRecord getByUserId(Long userId);

    AccessTokenRecord getByToken(String accessToken);
}
