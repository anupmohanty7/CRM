package com.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crm.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig{
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain springFilterChsin(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable());
		
		http.sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
);
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/api/auth/**","/api/customers/**","/api/followups/**").permitAll();
			auth.requestMatchers(
			        "/swagger-ui/**",	
			        "/v3/api-docs/**"
			).permitAll();
			auth.anyRequest().authenticated();
			
		});
		http.addFilterBefore(
		        jwtAuthenticationFilter,
		        UsernamePasswordAuthenticationFilter.class
		);
		return http.build();

	} 
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	
}
