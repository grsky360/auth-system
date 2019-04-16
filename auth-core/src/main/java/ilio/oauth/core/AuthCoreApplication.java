package ilio.oauth.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AuthCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCoreApplication.class, args);
    }
}
