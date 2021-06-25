package com.spring.project.root.dataacces;

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

import com.spring.project.root.repository.EntityModel;

@Entity(name = "alternatives")
@NamedQueries({ @NamedQuery(name = "Alternatives.getAll", query = "SELECT a FROM alternatives a"),
		@NamedQuery(name = "Alternatives.getByQuestion", query = "SELECT a FROM alternatives a where a.question = :question"),
		@NamedQuery(name = "Alternatives.getById", query = "SELECT q FROM alternatives q where q.id=:id") })
public class Alternatives implements EntityModel {

	private static final long serialVersionUID = 6677902374858756720L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "qid")
	private Question question;

	@Column(name = "alternative")
	private String alternative;

	@Column(name = "correct")
	private boolean correct;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @return the alternative
	 */
	public String getAlternative() {
		return alternative;
	}

	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * @param id
	 *           the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param question
	 *           the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @param alternative
	 *           the alternative to set
	 */
	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}

	/**
	 * @param correct
	 *           the correct to set
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Alternatives [id=" + id + ", question=" + question + ", alternative=" + alternative + ", correct=" + correct + "]";
	}
}
