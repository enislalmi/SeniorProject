package com.spring.project.root.dataacces;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import com.spring.project.root.repository.EntityModel;


@Entity(name="questions")
@NamedQueries({ 
	@NamedQuery(name = "Question.getAll", query = "SELECT q FROM questions q"),
	@NamedQuery (name = "Question.getHard", query = "SELECT q FROM questions q where q.hard=:hard"),
	@NamedQuery (name = "Question.getById", query = "SELECT q FROM questions q where q.id=:id")})

public class Question implements EntityModel {

private static final long serialVersionUID = 6677902374858756720L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long qid;
	
	@Column(name="question")
	private String question;
	
	@Column(name="hard")
	private int hard;
	
	@OneToMany(mappedBy = "question",fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Alternatives> alternatives = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	/**
	 * @return the qid
	 */
	public long getQid() {
		return qid;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @return the hard
	 */
	public int getHard() {
		return hard;
	}

	/**
	 * @return the alternatives
	 */
	public List<Alternatives> getAlternatives() {
		return alternatives;
	}

	/**
	 * @param qid the qid to set
	 */
	public void setQid(long qid) {
		this.qid = qid;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @param hard the hard to set
	 */
	public void setHard(int hard) {
		this.hard = hard;
	}

	/**
	 * @param alternatives the alternatives to set
	 */
	public void setAlternatives(List<Alternatives> alternatives) {
		this.alternatives = alternatives;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
}
