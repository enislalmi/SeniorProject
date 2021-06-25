package com.spring.project.root.dataacces;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import com.spring.project.root.repository.EntityModel;


@Entity(name="subject")
@NamedQueries({ 
	@NamedQuery(name = "Subject.getAll", query = "SELECT s FROM subject s") })
public class Subject implements EntityModel {
	
	private static final long serialVersionUID = 6677902374858756720L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subject_id;
	
	@Column(name="subject_name")
	private String subject_name;
	
	@OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Question> questions = new ArrayList<>();

	/**
	 * @return the subject_id
	 */
	public long getSubject_id() {
		return subject_id;
	}

	/**
	 * @return the subject_name
	 */
	public String getSubject_name() {
		return subject_name;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param subject_id the subject_id to set
	 */
	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	/**
	 * @param subject_name the subject_name to set
	 */
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	

}
