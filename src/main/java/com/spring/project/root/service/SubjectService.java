package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Subject;
import com.spring.project.root.repository.SubjectRepository;

/**
 * @author: Enis
 */
@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	/**
	 * get all {@link Subject}s
	 * 
	 * @return {@link List<Subject>}s
	 */
	public List<Subject> getList() {
		return this.subjectRepository.getAll();
	}
}
