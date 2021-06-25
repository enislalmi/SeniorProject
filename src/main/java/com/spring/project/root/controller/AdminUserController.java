package com.spring.project.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.project.root.dataacces.Alternatives;
import com.spring.project.root.dataacces.Question;
import com.spring.project.root.service.AlternativesService;
import com.spring.project.root.service.QuestionsService;
import com.spring.project.root.service.SubjectService;

/**
 * @author: Enis
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminUserController {

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private AlternativesService alternativesService;

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(path = "/dashboard", method = { RequestMethod.GET })
	public String doGetDashboard(final Model model) {
		return "admin/dashboard";
	}

	@RequestMapping(path = "/question/create", method = { RequestMethod.GET })
	public String getCreateQuestion(final Model model) {
		final Question question = new Question();
		model.addAttribute("subjects", this.subjectService.getList());
		for (int i = 0; i < 3; i++) {
			question.getAlternatives().add(new Alternatives());
		}
		model.addAttribute("question", question);
		return "admin/create-question";
	}

	@RequestMapping(path = "/question/create", method = RequestMethod.POST)
	public String doPost(final Model model, @ModelAttribute(value = "question") final Question question, final RedirectAttributes redirectAttributes) {
		boolean errorMessage = Boolean.FALSE;
		String view = "redirect:/admin/dashboard";
		if (question != null) {
			try {
				Question saved = this.questionsService.save(question);
				for (final Alternatives alternative : saved.getAlternatives()) {
					this.alternativesService.save(question, alternative);
				}
				return view;
			} catch (final Exception e) {
				view = "admin/create-question";
				errorMessage = Boolean.TRUE;
				model.addAttribute("errorMessage", errorMessage);
				return view;
			}
		}
		view = "admin/create-question";
		errorMessage = Boolean.TRUE;
		model.addAttribute("errorMessage", errorMessage);
		return view;
	}
}
