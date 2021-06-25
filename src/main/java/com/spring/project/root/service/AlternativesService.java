package com.spring.project.root.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Alternatives;
import com.spring.project.root.dataacces.Question;
import com.spring.project.root.repository.AlternativesRepository;

/**
 * @author: Enis
 */
@Service
public class AlternativesService {

	@Autowired
	private AlternativesRepository alternativesRepository;

	@Autowired
	private QuestionsService questionsService;

	public List<Alternatives> getList() {
		return this.alternativesRepository.getAll();
	}

	public Alternatives getById(final long id) {
		return this.alternativesRepository.getById(id);
	}

	public List<Alternatives> getByQuestion(final Question question) {
		return this.alternativesRepository.getByQuestion(question);
	}

	public List<Alternatives> getByQidAndCorrect(final long qid, final boolean correct) {
		List<Alternatives> alt = new ArrayList<Alternatives>();
		final Question question = this.questionsService.getById(qid);
		alt = this.getByQuestion(question).stream().filter(x -> x.isCorrect() == correct).collect(Collectors.toList());
		return alt;
	}

	@Transactional
	public Alternatives save(final Question question, final Alternatives alternative) {
		if (alternative != null) {
			alternative.setQuestion(question);
			alternative.setAlternative(alternative.getAlternative());
			alternative.setCorrect(alternative.isCorrect());
		}
		return this.alternativesRepository.save(alternative);
	}
}
