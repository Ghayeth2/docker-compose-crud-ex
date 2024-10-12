package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                // Authorizing POST, PUT, DELETE, GET from postman for testing only
                csrf(AbstractHttpConfigurer::disable).
                // Authorizing login request & setting the rest as authenticated
                authorizeHttpRequests(requests -> requests.requestMatchers(
                        "/login"
                ).permitAll().anyRequest().authenticated())
                // Authentication method is basic auth > spring security for testing only
                .httpBasic(Customizer.withDefaults()
                        )
                // Permitting sessions creation in case of server reloading
                // Spring basic auth prevents sessions it uses in-Memory JSESSIONID instead.
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(
                                        SessionCreationPolicy.ALWAYS
                                )
                );
        return http.build();
    }
}
