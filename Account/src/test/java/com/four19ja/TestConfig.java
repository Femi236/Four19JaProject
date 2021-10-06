package com.four19ja;


import com.four19ja.security.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestConfig {

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

}