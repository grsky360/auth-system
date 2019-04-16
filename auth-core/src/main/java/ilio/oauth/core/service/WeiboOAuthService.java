package ilio.oauth.core.service;

import ilio.oauth.core.config.properties.WeiboProperties;
import ilio.oauth.core.service.data.weibo.WeiboAccessToken;
import ilio.oauth.core.service.data.weibo.WeiboUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface WeiboOAuthService extends AbstractOAuthService {

    WeiboAccessToken getAccessToken(String code);

    WeiboUser getUserInfo(String accessToken, Long uid);
}
