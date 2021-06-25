package com.spring.project.root.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Enis
 */
@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

	@RequestMapping(method = { RequestMethod.GET })
	public String doLogout(final Model model, HttpServletRequest request) {
		try {
			request.logout();
		} catch (ServletException e) {
			e.getStackTrace();
		}
		return "redirect:/";
	}
}