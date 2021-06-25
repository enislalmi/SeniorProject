package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.AdminRole;
import com.spring.project.root.repository.AdminRoleRepository;

@Service
public class AdminUserRoleService {

	@Autowired
	private AdminRoleRepository adminRoleRepository;

	public AdminRole getById(final Long id) {
		return this.adminRoleRepository.getById(id);
	}

	public List<AdminRole> getList() {
		return this.adminRoleRepository.getAll();
	}
}
