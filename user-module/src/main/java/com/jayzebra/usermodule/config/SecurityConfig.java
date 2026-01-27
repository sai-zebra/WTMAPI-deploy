package com.jayzebra.usermodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF protection for stateless REST APIs
                .csrf(csrf -> csrf.disable())

                // 2. Define authorization rules
                .authorizeHttpRequests(authz -> authz
                        // You can make specific endpoints public here if needed
                        // .requestMatchers("/public/**").permitAll()
                        // For now, require authentication for all other requests
                        .anyRequest().authenticated()
                )

                // 3. Configure the default login form provided by Spring Security
                //    (This also provides a default /login endpoint)
                .formLogin(form -> form.permitAll())

                // 4. Configure the default logout behavior
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}

