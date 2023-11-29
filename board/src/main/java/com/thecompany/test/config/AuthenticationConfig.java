package com.thecompany.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class AuthenticationConfig {
	
	private final UserDetailsService userService;
	
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
				// .requestMatchers(toH2Console())
				.antMatchers("/static/**");
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests()
				.antMatchers("/", "/signup", "/join","/login","/info").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/")
				.defaultSuccessUrl("/home")
				.usernameParameter("email")
				.and()
				.logout()
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.and()
				.csrf().disable()
				.build();
	}
	
//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception{
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		
//		daoAuthenticationProvider.setUserDetailsService(userService);
//		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//		
//		return daoAuthenticationProvider;
//	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}