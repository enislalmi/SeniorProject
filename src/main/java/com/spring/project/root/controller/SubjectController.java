package com.spring.project.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.project.root.service.SubjectService;

/***
 * @author: Enis
 */
@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String doGet(final Model model) {
		model.addAttribute("subjects", this.subjectService.getList());
		return "subject/read";
	}
}