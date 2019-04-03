package ilio.auth.support;

import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

@Component
@Conditional(EnableAuthCondition.class)
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthTokenCache authTokenCache;

    static final String ATTRIBUTE_USER = "__user__";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        AuthRequired authUser = getAuthUser((HandlerMethod) handler, AuthRequired.class);
        if (authUser == null) {
            return true;
        }
        String token = getToken(request);
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        User user = authTokenCache.getUser(token);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        request.setAttribute(ATTRIBUTE_USER, user);
        return true;
    }

    private <T extends Annotation> T getAuthUser(HandlerMethod handler, Class<T> clazz) {
        T annotation = handler.getMethodAnnotation(clazz);
        if (annotation == null) {
            annotation = handler.getBean().getClass().getAnnotation(clazz);
        }
        return annotation;
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtil.isEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.substring("Bearer".length());
    }
}
