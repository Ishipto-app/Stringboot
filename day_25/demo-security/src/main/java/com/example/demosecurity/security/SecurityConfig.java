package com.example.demosecurity.security;

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
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                //ko check
                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                //nhieu role co the vao
//                .requestMatchers(new AntPathRequestMatcher("/user")).hasAnyRole("USER", "ADMIN")
//                //1 role co the vao
//                .requestMatchers(new AntPathRequestMatcher("/admin")).hasRole("ADMIN")
//                //.requestMatchers(new AntPathRequestMatcher("/author")).hasRole("AUTHOR")
//                //hasAuthority ~ hasRole can bo sung them ROLE_
//                .requestMatchers(new AntPathRequestMatcher("/author")).hasAuthority("ROLE_AUTHOR")
                //phai login thanh cong thi vao dc
                .anyRequest().authenticated()
                .and()
                //hien thi form login co san
                .formLogin()
                .and()
                //hien thi
                .logout()
                .logoutSuccessUrl("/");

        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("USER", "ADMIN")
                .build();
        UserDetails author = User.withDefaultPasswordEncoder()
                .username("author")
                .password("123")
                .roles("AUTHOR")
                .build();
        return new InMemoryUserDetailsManager(user, admin, author);
    }
}
