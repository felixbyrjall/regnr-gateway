package com.github.felixbyrjall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeExchange(auth -> auth
						.pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.pathMatchers("/api/csrf").permitAll()
						.pathMatchers("/api/auth/**").permitAll()
						.pathMatchers("/actuator/**").permitAll()
						.anyExchange().permitAll()
				)
				.httpBasic(httpBasic -> httpBasic.disable())
				.formLogin(formLogin -> formLogin.disable())
				.build();
	}
}
