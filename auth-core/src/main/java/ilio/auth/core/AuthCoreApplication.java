package ilio.auth.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AuthCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCoreApplication.class, args);
    }
}
