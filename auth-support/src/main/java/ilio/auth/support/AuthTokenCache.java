package ilio.auth.support;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.madvoc.result.JsonResult;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@Conditional(EnableAuthCondition.class)
class AuthTokenCache {

    @Value("${auth.url: http://localhost:9001}")
    private String authUrl;
    private LoadingCache<String, Optional<User>> authTokenCache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Optional<User>>() {
                    @Override
                    public Optional<User> load(String key) throws Exception {
                        return auth(key);
                    }
                });

    public User getUser(String token) {
        try {
            Optional<User> cache = authTokenCache.get(token);
            if (cache.isPresent()) {
                return cache.get();
            }
            authTokenCache.invalidate(token);
        } catch (Exception e) {
            log.error("auth failed", e);
        }
        return null;
    }

    private Optional<User> auth(String token) {
        if (StringUtil.isEmpty(token)) {
            return Optional.empty();
        }
        try {
            HttpResponse response = HttpRequest.get(authUrl + "/api/auth").header("Authorization", "Bearer " + token).send();
            if (response.statusCode() == 401) {
                return Optional.empty();
            }
            JsonResult jsonResult = JSON.parseObject(response.body(), JsonResult.class);
            if (response.statusCode() != 200) {
                throw new RuntimeException("auth failed, code: " + jsonResult.status() + ", message: " + jsonResult.message());
            }
            response.close();
            User user = JSON.parseObject(jsonResult.value(), User.class);
            if (user == null) {
                return Optional.empty();
            }
            return Optional.of(user);
        } catch (Exception e) {
            throw new RuntimeException("auth failed", e);
        }
    }
}
