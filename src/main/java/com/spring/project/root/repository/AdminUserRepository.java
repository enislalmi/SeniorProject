package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.spring.project.root.dataacces.AdminUser;

/**
 * @author: Enis
 */
@Repository
public class AdminUserRepository extends BasicRepository<AdminUser> {

	public AdminUserRepository() {
		super(AdminUser.class);
	}

	@Override
	public List<AdminUser> getAll() {
		TypedQuery<AdminUser> query = entityManager.createNamedQuery("AdminUser.getAll", AdminUser.class);
		List<AdminUser> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}

	public AdminUser getById(final Long id) {
		TypedQuery<AdminUser> query = entityManager.createNamedQuery("AdminUser.getById", AdminUser.class);
		query.setParameter("id", id);
		AdminUser element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}

	public AdminUser getByUsername(final String username) {
		TypedQuery<AdminUser> query = entityManager.createNamedQuery("AdminUser.getByUsername", AdminUser.class);
		query.setParameter("username", username);
		AdminUser element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
}
