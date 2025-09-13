package com.nhn.app.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAspectJAutoProxy
public class ExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

}
