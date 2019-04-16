package ilio.oauth.core.service;

import ilio.oauth.core.service.data.facebook.FacebookAccessToken;
import ilio.oauth.core.service.data.facebook.FacebookDebugToken;

public interface FacebookOAuthService extends AbstractOAuthService {

    FacebookAccessToken getAccessToken(String code);

    FacebookDebugToken checkAccessToken(String accessToken);
}
