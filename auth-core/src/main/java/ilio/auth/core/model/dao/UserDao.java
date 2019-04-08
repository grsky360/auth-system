package ilio.auth.core.model.dao;

import ilio.auth.core.model.generated.tables.records.UserRecord;

public interface UserDao {

    UserRecord getByUserNameAndPassword(String username, String encryptedPassword);

    UserRecord getByUserId(Long userId);
}
