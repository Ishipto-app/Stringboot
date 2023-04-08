package com.example.exercise_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
//su dung web security
@EnableWebSecurity
//su dung method security
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {
    //.roles("ADMIN", "SALE", "AUTHOR", "USER")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/dashboard").hasAnyRole("ADMIN", "SALE")
                .requestMatchers("/user").hasAnyRole("ADMIN")
                .requestMatchers("/category").hasAnyRole("ADMIN", "SALE")
                .requestMatchers("/product").hasAnyRole("ADMIN", "SALE")
                .requestMatchers("/brand").hasAnyRole("ADMIN", "SALE")
                .requestMatchers("/order").hasAnyRole("ADMIN", "SALE")
                .requestMatchers("/author").hasAnyRole("ADMIN", "SALE", "AUTHOR")
                .requestMatchers("/profile").hasAnyRole("ADMIN", "SALE", "AUTHOR", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");

        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMIN", "SALE", "AUTHOR", "USER")
                .build();
        UserDetails sale = User.withDefaultPasswordEncoder()
                .username("sale")
                .password("123")
                .roles("SALE", "AUTHOR", "USER")
                .build();
        UserDetails author = User.withDefaultPasswordEncoder()
                .username("author")
                .password("123")
                .roles("AUTHOR", "USER")
                .build();
        return new InMemoryUserDetailsManager(admin, sale, author);
    }
}

