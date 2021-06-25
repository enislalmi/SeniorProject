package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.AdminUser;
import com.spring.project.root.repository.AdminUserRepository;

/**
 * @author: Enis
 */
@Service
public class AdminUserService {

	@Autowired
	private AdminUserRepository adminUserRepository;

	public List<AdminUser> getList() {
		return this.adminUserRepository.getAll();
	}

	public AdminUser getById(final long id) {
		return this.adminUserRepository.getById(id);
	}

	public AdminUser getByUsername(final String username) {
		return this.adminUserRepository.getByUsername(username);
	}

	public void setAdminUserRepository(final AdminUserRepository adminUserRepository) {
		this.adminUserRepository = adminUserRepository;
	}
}
