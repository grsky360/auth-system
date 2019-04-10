package ilio.auth.support;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.Assert;

import java.util.Map;

public class EnableAuthCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> map = context.getBeanFactory().getBeansWithAnnotation(EnableAuth.class);
        return !map.isEmpty();
    }
}
