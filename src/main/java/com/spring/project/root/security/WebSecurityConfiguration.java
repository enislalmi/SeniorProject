package com.spring.project.root.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: Enis
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/resources/**").permitAll().antMatchers("/css/**").permitAll().antMatchers("/js/**").permitAll()
				.antMatchers("/static/**").permitAll().antMatchers("/index/**").permitAll().antMatchers("/favicon.ico").permitAll().antMatchers("/templates/**").permitAll();
	}
}
