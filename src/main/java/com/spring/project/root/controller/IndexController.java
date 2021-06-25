package com.spring.project.root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * @author: Enis
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String doGet(final Model model) {
		return "index/index";
	}
}