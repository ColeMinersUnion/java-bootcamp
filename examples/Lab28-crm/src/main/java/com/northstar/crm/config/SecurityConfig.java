package com.northstar.crm.config;

import com.northstar.crm.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;


@Configuration
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
    http.csrf(csrf -> csrf.disable());
    // TODO: sessionManagement STATELESS
    // TODO: authorizeHttpRequests:
    //   /api/auth/login, /actuator/health, /error permitAll
    //   /api/customers/** hasAnyRole("AGENT","ADMIN")
    //   /api/admin/** hasRole("ADMIN")
    //   anyRequest authenticated
    //   (permit /error so live Tomcat does not rewrite 403 → 401)
    // TODO: addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login", "/actuator/health", "/error").permitAll()
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/customers/**").hasAnyRole("AGENT", "ADMIN")
            .anyRequest().authenticated());
    return http.build();
  }
}
