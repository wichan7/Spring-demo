package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}

	@Bean
	SecurityFilterChain appSecurity(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
	    http
	        .authorizeHttpRequests((authorize) -> authorize
	            .requestMatchers(mvc.pattern("/")).permitAll()
	            .requestMatchers(mvc.pattern("/board/**")).permitAll()
	            .requestMatchers(mvc.pattern("/member/register")).permitAll()
	            .requestMatchers(mvc.pattern("/member/logout")).permitAll()
	            .requestMatchers(mvc.pattern("/error")).permitAll()
	            .requestMatchers(mvc.pattern("/assets/**")).permitAll()
	            .requestMatchers(mvc.pattern("/favicon.ico")).permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin((form) -> form
	        	.loginPage("/member/login")
	        	.loginProcessingUrl("/member/login")
	        	.usernameParameter("uid")
	        	.passwordParameter("upw")
	        	.defaultSuccessUrl("/")
	        	.failureUrl("/member/login")
	        	.permitAll()
	        )
	        .logout(logout -> logout
	        	.logoutUrl("/member/logout")
	        	.logoutSuccessUrl("/")
	        	.invalidateHttpSession(true)
	        	.deleteCookies("JSESSIONID")
	        	.permitAll()
	        );

	    return http.build();
	}
	
}