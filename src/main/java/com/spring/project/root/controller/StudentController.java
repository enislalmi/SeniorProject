package com.spring.project.root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Enis
 */
@Controller
@RequestMapping(path = "/student")
public class StudentController {

	@RequestMapping(path = "/dashboard", method = { RequestMethod.GET })
	public String doGetDashboardStudent(final Model model) {
		return "index/index";
	}
}