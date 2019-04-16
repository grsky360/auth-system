package ilio.oauth.core.service;

import ilio.oauth.core.model.generated.tables.records.UserRecord;
import ilio.oauth.core.service.data.AccessToken;

public interface AuthService {

    AccessToken login(String username, String password);

    UserRecord getUserInfo(String accessToken);
}
