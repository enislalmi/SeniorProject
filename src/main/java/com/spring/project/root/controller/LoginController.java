package com.spring.project.root.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.project.root.dataacces.AdminUser;
import com.spring.project.root.service.AdminUserService;

/**
 * @author: Enis
 */
@Controller
@RequestMapping(path = "/")
public class LoginController {

	@Autowired
	private AdminUserService adminUserService;

	private static final String ERROR_MESSAGE = "Wrong Credentials.";

	@RequestMapping(method = { RequestMethod.GET })
	public String doLogin(final HttpServletRequest request, final Model model) {
		model.addAttribute("adminUser", new AdminUser());
		return "login/login";
	}

	@RequestMapping(path = "login", method = { RequestMethod.POST })
	public String doLogin(final RedirectAttributes redirectAttributes, final Model model, HttpSession session, final HttpServletRequest request,
			@ModelAttribute(value = "adminUser") final AdminUser adminUser) {
		final String username = adminUser.getUsername();
		final String password = adminUser.getPassword();
		try {
			final AdminUser user = this.adminUserService.getList().stream().map(u -> this.adminUserService.getByUsername(username)).findFirst().orElse(null);
			if (user != null) {
				final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				final GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRole());
				grantedAuthorities.add(authority);
				final Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(auth);
				session = request.getSession();
				session.setAttribute("admin", user);
				if (user.getRole().getRole().equals("ROLE_ADMIN")) {
					return "redirect:/admin/dashboard";
				} else {
					return "redirect:/index";
				}
			}
			model.addAttribute("loginError", LoginController.ERROR_MESSAGE);
		} catch (final Exception e) {
			model.addAttribute("loginError", LoginController.ERROR_MESSAGE);
			return "login/login";
		}
		return "login/login";
	}
}