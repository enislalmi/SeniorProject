package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacces.Alternatives;
import com.spring.project.root.dataacces.Question;

/**
 * @author: Enis
 */
@Repository
public class AlternativesRepository extends BasicRepository<Alternatives> {

	public AlternativesRepository() {
		super(Alternatives.class);
	}

	@Override
	public List<Alternatives> getAll() {
		TypedQuery<Alternatives> query = entityManager.createNamedQuery("Alternatives.getAll", Alternatives.class);
		List<Alternatives> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}

	public List<Alternatives> getByQuestion(final Question question) {
		TypedQuery<Alternatives> query = entityManager.createNamedQuery("Alternatives.getByQuestion", Alternatives.class);
		query.setParameter("question", question);
		List<Alternatives> list = query.getResultList();
		return ObjectUtils.isEmpty(list) ? null : list;
	}

	public Alternatives getById(final Long id) {
		TypedQuery<Alternatives> query = entityManager.createNamedQuery("Alternatives.getById", Alternatives.class);
		query.setParameter("id", id);
		Alternatives element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
}
