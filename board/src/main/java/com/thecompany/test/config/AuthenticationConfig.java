package com.thecompany.test.config;

import com.thecompany.test.jwt.JwtFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {


    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
	        .authorizeRequests() // 요청에 대한 권한 설정
	        .antMatchers("/main").authenticated()
	        .anyRequest().permitAll();
        
        httpSecurity
        .formLogin() // Form Login 설정
            .loginPage("/")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")
        .and()
            .logout()
        .and()
            .csrf().disable();
        
//                .httpBasic().disable()
//                .csrf().disable()
//                .cors().and()
//                .headers().frameOptions().sameOrigin().and()
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//                //.antMatchers("/member/login").permitAll()
//                .antMatchers("/member/join").permitAll()
//                .antMatchers("/member/refreshToken").permitAll()
//                .antMatchers(HttpMethod.POST, "/member/**").authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(new JwtFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
                return httpSecurity.build();
    }



}