package com.spring.project.root.dataacces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

/**
 * @author: Enis
 */
@Entity(name = "adminrole")
@NamedQueries({ @NamedQuery(name = "AdminRole.getAll", query = "SELECT e FROM adminrole e"),
		@NamedQuery(name = "AdminRole.getByName", query = "SELECT e FROM adminrole e WHERE e.role = :role"),
		@NamedQuery(name = "AdminRole.getById", query = "SELECT e FROM adminrole e WHERE e.id = :id") })
@Table(name = "adminrole")
public class AdminRole implements EntityModel {

	private static final long serialVersionUID = 70214003617813728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String role;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param id
	 *           the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param role
	 *           the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
