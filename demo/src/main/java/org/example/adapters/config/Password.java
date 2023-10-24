//package org.example.adapters.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableGlobalAuthentication()
//public class Password{
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }

//    @Bean
//    public void cofiguration (HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable();
//    }
//


