package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.ecom.service.CustomerService;
import com.ecom.service.CustomerServiceImpl;
import com.ecom.service.MyUserDetailsService;

import lombok.SneakyThrows;
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private MyUserDetailsService userDetailsService;
		@Bean
		public BCryptPasswordEncoder pwdEncoder() {
			return new BCryptPasswordEncoder();
		}
		@Bean
		public AuthenticationProvider authProvider() {
			DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService);
			authProvider.setPasswordEncoder(pwdEncoder());
			return authProvider;
		}
		@Bean
		@SneakyThrows
		public AuthenticationManager authmanager(AuthenticationConfiguration config) {
			return config.getAuthenticationManager();
		}
//		@Bean
//		@SneakyThrows
//		public SecurityFilterChain security(HttpSecurity http) {
//			http.authorizeHttpRequests(req->{
//				req.requestMatchers("/register","/login","/resetPwd","/forgetPwd")
//				.permitAll()
//				.anyRequest()
//				.authenticated();
//			});
//			return http.csrf().disable().build();
//		}
		@Bean
		@SneakyThrows
		public SecurityFilterChain security(HttpSecurity http) {
		    http
		        .csrf(csrf -> csrf.disable()) 
		        .authorizeHttpRequests(req -> req
		        .requestMatchers("/register", "/login", "/resetPwd", "/forgetPwd/{email}").permitAll()
		        .anyRequest()
		        .authenticated()
		        );
		    return http.build(); 
		}




}
