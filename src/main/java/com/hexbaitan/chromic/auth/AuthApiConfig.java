package com.hexbaitan.chromic.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthApiConfig {

    @Bean
    public SecurityFilterChain filterChainForSignup(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.securityMatcher("/api/v1/auth/**")
                .authorizeHttpRequests(http ->
                        http.requestMatchers("/api/v1/auth/**").permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
        ;

        return httpSecurity.build();
    }

}
