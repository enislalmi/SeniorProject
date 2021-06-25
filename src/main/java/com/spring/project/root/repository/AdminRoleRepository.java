package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.spring.project.root.dataacces.AdminRole;

/**
 * @author: Enis
 */
@Repository
public class AdminRoleRepository extends BasicRepository<AdminRole> {

	public AdminRoleRepository() {
		super(AdminRole.class);
	}

	@Override
	public List<AdminRole> getAll() {
		TypedQuery<AdminRole> query = entityManager.createNamedQuery("AdminRole.getAll", AdminRole.class);
		List<AdminRole> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}

	public AdminRole getByName(final String role) {
		TypedQuery<AdminRole> query = entityManager.createNamedQuery("AdminRole.getByName", AdminRole.class);
		query.setParameter("role", role);
		AdminRole element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}

	public AdminRole getById(final Long id) {
		TypedQuery<AdminRole> query = entityManager.createNamedQuery("AdminRole.getById", AdminRole.class);
		query.setParameter("id", id);
		AdminRole element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
}
