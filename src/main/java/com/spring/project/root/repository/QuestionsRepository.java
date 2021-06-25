package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacces.Question;

@Repository
public class QuestionsRepository extends BasicRepository<Question> {

	public QuestionsRepository() {
		super(Question.class);
	}

	@Override
	public List<Question> getAll() {
		TypedQuery<Question> query = entityManager.createNamedQuery("Question.getAll", Question.class);
		List<Question> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}

	@Override
	public List<Question> getByHard(final int hard) {
		TypedQuery<Question> query = entityManager.createNamedQuery("Question.getHard", Question.class);
		query.setParameter("hard", hard);
		List<Question> list = query.getResultList();
		return ObjectUtils.isEmpty(list) ? null : list;
	}

	public Question getById(final Long id) {
		TypedQuery<Question> query = entityManager.createNamedQuery("Question.getById", Question.class);
		query.setParameter("id", id);
		Question element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
}
