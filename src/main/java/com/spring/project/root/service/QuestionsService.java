package com.spring.project.root.service;

import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Alternatives;
import com.spring.project.root.dataacces.Question;
import com.spring.project.root.repository.QuestionsRepository;

/**
 * @author: Enis
 */
@Service
public class QuestionsService {

	@Autowired
	private QuestionsRepository questionsRepository;

	/**
	 * get all {@link Questions}s
	 * 
	 * @return {@link List<Questions>}s
	 */
	public List<Question> getList() {
		return this.questionsRepository.getAll();
	}

	public Question getRandomQuestion(List<Question> questions) {
		return questions.get(new Random().nextInt(questions.size()));
	}

	public Question getRandomWithHardness(final int hardness, int currentQuestionId, final int subjectId) {
		System.out.println("Difficulty: " + hardness);
		final List<Question> questions = this.questionsRepository.getByHard(hardness);
		if (questions == null) {
			return null;
		} else {
			for (Question question : questions) {
				if (question.getQid() != currentQuestionId && question.getSubject().getSubject_id() == subjectId) {
					return question;
				}
			}
		}
		return null;
	}

	public Question getById(final long id) {
		return this.questionsRepository.getById(id);
	}

	int diff = 10;

	public Question getNextQuestion(final int currentQuestionId, final int questionNumber, final Alternatives answer, final int subjectId) {
		int lowestDiff = 5;
		int right = 0;
		int finaldiff = 0;
		Question question = new Question();
		if (questionNumber <= 1) {
			question = this.getRandomWithHardness(lowestDiff, currentQuestionId, subjectId);
			return question;
		} else if (questionNumber == 2) {
			question = this.getRandomWithHardness(diff, currentQuestionId, subjectId);
			return question;
		}
		// 1,2,3 ,4, 5,6,7,8
		else {
			if (answer.isCorrect()) {
				finaldiff = question.getHard();
				diff = diff + (2 * questionNumber);
			} else {
				diff = diff - (2 % questionNumber);
			}
			question = this.getRandomWithHardness(diff, currentQuestionId, subjectId);
		}
		return question;
	}

	@Transactional
	public Question save(final Question question) {
		if (question != null) {
			question.setQuestion(question.getQuestion());
			question.setSubject(question.getSubject());
			question.setHard(question.getHard());
		}
		return this.questionsRepository.save(question);
	}
}