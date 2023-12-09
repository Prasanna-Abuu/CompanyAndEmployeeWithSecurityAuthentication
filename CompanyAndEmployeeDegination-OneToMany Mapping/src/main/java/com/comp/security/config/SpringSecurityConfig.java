package com.comp.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.comp.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

	// authentication
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoUserDetailsService();
	}

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeHttpRequests()
				.requestMatchers("/hello", "/company/add", "/employee/add", "/user/save").permitAll()
				.requestMatchers("/company/**").authenticated().and().httpBasic();
		;

		return http.build();
	}

	@Bean

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}

/*
 * @Bean public UserDetailsService userDetailsService(PasswordEncoder encoder) {
 * UserDetails admin =
 * User.withUsername("Tata").password(encoder.encode("12345")).roles("ADMIN").
 * build();
 * 
 * UserDetails user =
 * User.withUsername("Prasanna").password(encoder.encode("12345@PA")).roles(
 * "USER").build(); return new InMemoryUserDetailsManager(admin, user);
 * 
 * }
 */


