package com.anwjrrp33.club.config;

import lombok.extern.log4j.Log4j2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// 	// 사용자 계정은 user1
	// 	auth.inMemoryAuthentication().withUser("user1")
	// 	// 1111 패스워드 인코딩 결과
	// 		.password("$2a$10$pcu.wxQrbd0e/1kKxpqJKeu8OwgRLR5b1vq6l3V4XFEOiYF8FcQVq")
	// 		.roles("USER");
	// }

	// 새로 추가
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/sample/all").permitAll()
			.antMatchers("/sample/member").hasRole("USER");

		http.formLogin(); // 인가 및 인증에 문제시 로그인 화면
		http.csrf().disable(); // CSRF 토큰을 발행하지 않도록 설정
		http.logout(); // 로그아웃

		http.oauth2Login();
	}
}
