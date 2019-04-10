package ilio.auth.support;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthConfiguration.class)
public @interface EnableAuth {

}
