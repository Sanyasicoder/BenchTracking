package com.centric.bench.security;

import static org.springframework.security.config.Customizer.withDefaults;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {	
		
		return http
	            .csrf(csrf -> csrf.disable()) // (1)
	            .authorizeRequests( auth -> {
	                auth.anyRequest().authenticated(); // (2)
	            })
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // (3)
	            .httpBasic(withDefaults()) // (4)
	            .build();
	        
	    
	}
	
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

	    UserDetails user = User.withDefaultPasswordEncoder() // (1)
	            .username("user")
	            .password("pass")
	            .roles("USER")
	            .build();

	    UserDetails admin = User.withDefaultPasswordEncoder() // (2)
	            .username("admin")
	            .password("passs")
	            .roles("USER","ADMIN")
	            .build();
	    
	    UserDetails hr = User.withDefaultPasswordEncoder() // (2)
	            .username("hr")
	            .password("pass")
	            .roles("USER","ADMIN")
	            .build();
	    
		/*
		 * UserDetails superuser = User.withDefaultPasswordEncoder() // (2)
		 * .username("superuser") .password("pass") .roles("USER","ADMIN","SUPER")
		 * .build();
		 */

	    return new InMemoryUserDetailsManager(user, admin,hr); // (3)
	}
	
	
	

}
