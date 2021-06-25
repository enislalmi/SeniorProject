package com.spring.project.root.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacces.Question;

public abstract class BasicRepository<T extends EntityModel> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected final Class<T> type;

	protected BasicRepository(final Class<T> type) {
		this.type = type;
	}

	/**
	 * append order by
	 *
	 * @param orderBy
	 * @param sqlQuery
	 */
	private void appendOrderBy(final String orderBy, final StringBuilder sqlQuery) {
		if (StringUtils.isNotBlank(orderBy)) {
			sqlQuery.append(StringUtils.SPACE);
			sqlQuery.append(orderBy);
			sqlQuery.append(StringUtils.SPACE);
		}
	}

	/**
	 * delete given {@link EntityModel} from database
	 *
	 * @param t
	 */
	public void delete(final T entity) {
		this.entityManager.remove(entity);
	}

	private TypedQuery<T> getQuery(final StringBuilder sqlQuery) {
		return this.entityManager.createQuery(this.normalizeSQL(sqlQuery), this.type);
	}

	/**
	 * get all {@link EntityModel} from database
	 *
	 * @return {@link List}
	 */
	public List<T> getAll() {
		List<T> all = new ArrayList<>();
		try {
			final TypedQuery<T> query = this.getQuery(this.getFromQuery());
			all = query.getResultList();
		} catch (final NoResultException e) {
			Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
		}
		return all;
	}

	/**
	 * get all {@link EntityModel} from database
	 *
	 * @param orderBy
	 * @return {@link List}
	 */
	public List<T> getAll(final String orderBy) {
		List<T> all = new ArrayList<>();
		try {
			final StringBuilder sqlQuery = this.getFromQuery();
			this.appendOrderBy(orderBy, sqlQuery);
			final TypedQuery<T> query = this.getQuery(sqlQuery);
			all = query.getResultList();
		} catch (final NoResultException e) {
			Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage(), e);
		}
		return all;
	}

	public List<Question> getByHard(final int hard) {
		TypedQuery<Question> query = entityManager.createNamedQuery("Question.getHard", Question.class);
		query.setParameter("hard", hard);
		List<Question> list = query.getResultList();
		return ObjectUtils.isEmpty(list) ? null : list;
	}

	private String normalizeSQL(final CharSequence sql) {
		return StringUtils.stripToEmpty(sql.toString().replaceAll(" +", StringUtils.SPACE));
	}

	/**
	 * save {@link EntityModel} in database
	 *
	 * @param t
	 */
	public T save(final T entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	/**
	 * save or update a {@link EntityModel} in database. use this method if you use manual IDs
	 *
	 * @param entity
	 * @return {@link EntityModel} of type T
	 */
	public T saveOrUpdate(final T entity) {
		return this.update(entity);
	}

	private StringBuilder getFromQuery() {
		final StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("FROM ");
		sqlQuery.append(this.type.getSimpleName());
		sqlQuery.append(" t ");
		return sqlQuery;
	}

	/**
	 * update {@link EntityModel} in database
	 *
	 * @param t
	 * @return {@link EntityModel} of type T
	 */
	public T update(final T entity) {
		return this.entityManager.merge(entity);
	}

	public void refresh(T entity) {
		entityManager.refresh(this.entityManager.merge(entity));
	}
}
