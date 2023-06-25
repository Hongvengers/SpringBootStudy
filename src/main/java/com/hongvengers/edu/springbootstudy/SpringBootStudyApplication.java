package com.hongvengers.edu.springbootstudy;

import com.hongvengers.edu.config.MySpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MySpringBootApplication
public class SpringBootStudyApplication {

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }
}
