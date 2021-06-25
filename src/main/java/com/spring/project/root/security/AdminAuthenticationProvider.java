package com.spring.project.root.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.spring.project.root.dataacces.AdminUser;
import com.spring.project.root.service.AdminUserService;

/**
 * @author: Enis
 */
@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AdminUserService adminUserService;

	private static final String ERROR_MESSAGE = "Invalid username or password";

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		if (StringUtils.isBlank(authentication.getName())) {
			throw new BadCredentialsException(AdminAuthenticationProvider.ERROR_MESSAGE);
		}
		final String username = authentication.getName();
		final String password = authentication.getCredentials().toString();
		final AdminUser adminUser = this.adminUserService.getList().stream().filter(u -> u.match(username, password)).findFirst().orElse(null);
		if (adminUser != null) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority(adminUser.getRole().toString()));
			Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
			return auth;
		} else {
			throw new BadCredentialsException(AdminAuthenticationProvider.ERROR_MESSAGE);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
