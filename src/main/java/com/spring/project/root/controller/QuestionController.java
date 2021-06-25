package com.spring.project.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.project.root.dataacces.Alternatives;
import com.spring.project.root.dataacces.Question;
import com.spring.project.root.service.AlternativesService;
import com.spring.project.root.service.QuestionsService;

/***
 * @author: Enis
 */
@Controller
public class QuestionController {

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private AlternativesService alternativesService;

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String doGet(@RequestParam(name = "q") int questionNumber, @RequestParam(name = "sid") int subjectId, final Model model) {
		if (questionNumber == 0) {
			Question initial = this.questionsService.getRandomWithHardness(5, 11, subjectId);
			model.addAttribute("question", initial);
		}
		model.addAttribute("questionNumber", questionNumber + 1);
		return "questions/question";
	}

	@RequestMapping(value = "/questions/show", method = RequestMethod.POST)
	public String doPost(@RequestParam(name = "q") int questionNumber, @RequestParam(name = "qid") int currentQuestionId, @RequestParam(name = "aid") int answerId,
			RedirectAttributes redirectAttributes, Model model) {
		final Question currentQuestion = this.questionsService.getById(currentQuestionId);
		final int subjectId = (int) currentQuestion.getSubject().getSubject_id();
		String view = "redirect:/questions?q=" + questionNumber + "&sid=" + subjectId;
		int result = 0;
		final Alternatives answer = this.alternativesService.getById(answerId);
		if (questionNumber < 7) {
			if (questionNumber == 6) {
				final Question finalQuestion = this.questionsService.getById(currentQuestionId);
				result = finalQuestion.getHard();
				model.addAttribute("result", result);
				view = "questions/result";
				return view;
			}
			Question question = this.questionsService.getNextQuestion(currentQuestionId, questionNumber, answer, subjectId);
			redirectAttributes.addFlashAttribute("question", question);
		}
		return view;
	}
}