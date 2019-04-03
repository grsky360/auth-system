package ilio.auth.core.service;

import ilio.auth.core.model.generated.tables.records.AccessTokenRecord;
import ilio.auth.core.model.generated.tables.records.UserRecord;

public interface AuthService {

    AccessTokenRecord login(String username, String password);

    UserRecord getUserInfo(String accessToken);

    boolean isValid(String accessToken);

    boolean renewToken(String accessToken);
}
