package ilio.auth.core.service;

import ilio.auth.core.model.generated.tables.records.UserRecord;
import ilio.auth.core.service.data.AccessToken;

public interface AuthService {

    AccessToken login(String username, String password);

    UserRecord getUserInfo(String accessToken);
}
