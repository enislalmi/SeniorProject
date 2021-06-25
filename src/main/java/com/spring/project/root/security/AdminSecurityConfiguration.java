package com.spring.project.root.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Enis
 */
@Configuration
@Order(1)
@EnableWebSecurity
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AdminAuthenticationProvider adminAuthenticationProvider;

	private static String ADMIN_INDEX = "/";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable();
		http.formLogin().loginPage("/login.html").and().authorizeRequests().antMatchers("/admin/**").authenticated().and().authorizeRequests().antMatchers("/student/**")
				.authenticated();
		http.logout().logoutUrl("/logout").logoutSuccessUrl(AdminSecurityConfiguration.ADMIN_INDEX).invalidateHttpSession(true).deleteCookies("JESSIONID",
				"SPRING_SECURITY_REMEMBER_ME_COOKIE");
		http.authenticationProvider(this.adminAuthenticationProvider);
		http.headers().frameOptions().sameOrigin();
		http.headers().cacheControl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
