package com.auanjulio.hotelbookingapi.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String [] ENDPOINTS_WITH_AUTH_NOT_REQUIRED = {
            "/auth/login",
            "/auth/register"
    };
}
