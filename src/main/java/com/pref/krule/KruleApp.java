package com.pref.krule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableWebMvc
@EnableOpenApi
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class KruleApp {

    public static void main(String[] args) {
        SpringApplication.run(KruleApp.class, args);
    }

}
