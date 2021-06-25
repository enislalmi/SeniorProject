package com.spring.project.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.project.root.dataacces.AdminUser;
import com.spring.project.root.dataacces.Question;
import com.spring.project.root.repository.QuestionsRepository;
import com.spring.project.root.service.AdminUserService;
import com.spring.project.root.service.QuestionsService;

@Controller
public class NewQuestionsController {

	

	@Autowired
	private QuestionsRepository questionRepository;
	
	  @GetMapping("/createquestions")
	  public String createQuestions(Model model) {
	    model.addAttribute("question", new Question());
	    return "question";
	  }

	  @PostMapping("/createquestions")
	  public String greetingSubmit(@ModelAttribute Question question) {
	    Question newQuestion = new Question();
	    newQuestion.setQuestion(question.getQuestion());
	    newQuestion.setHard(question.getHard());
	    newQuestion.setSubject(question.getSubject());
	    newQuestion.setAlternatives(question.getAlternatives());
	    newQuestion.setQid(question.getQid());
	    return "newQuestion";
	  }
}
