package com.thecompany.test.config;

import com.thecompany.test.jwt.JwtFilter;
import com.thecompany.test.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

//	@Autowired
//	JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	
        httpSecurity
	        .authorizeRequests() // 요청에 대한 권한 설정
//	        .antMatchers("/main").authenticated()
	        .anyRequest().permitAll();
        httpSecurity.httpBasic();
        httpSecurity
        .formLogin() // Form Login 설정
            .loginPage("/")
            .loginProcessingUrl("/api/login")
            .defaultSuccessUrl("/main")
        .and()
            .logout()
        .and()
            .csrf().disable();
        
//        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
    }



}