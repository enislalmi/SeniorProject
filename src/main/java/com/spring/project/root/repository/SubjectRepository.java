package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.spring.project.root.dataacces.Subject;

@Repository
public class SubjectRepository extends BasicRepository<Subject> {

	public SubjectRepository() {
		super(Subject.class);
	}

	@Override
	public List<Subject> getAll() {
		TypedQuery<Subject> query = entityManager.createNamedQuery("Subject.getAll", Subject.class);
		List<Subject> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
}
