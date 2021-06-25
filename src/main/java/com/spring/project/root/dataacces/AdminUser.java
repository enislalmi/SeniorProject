package com.spring.project.root.dataacces;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

/**
 * @author: Enis
 */
@Entity(name = "adminuser")
@NamedQueries({ @NamedQuery(name = "AdminUser.getAll", query = "SELECT e FROM adminuser e"),
		@NamedQuery(name = "AdminUser.getById", query = "SELECT e FROM adminuser e WHERE e.id = :id"),
		@NamedQuery(name = "AdminUser.getByUsername", query = "SELECT e FROM adminuser e WHERE e.username = :username") })
@Table(name = "adminuser")
public class AdminUser implements EntityModel {

	private static final long serialVersionUID = 9146633329467557718L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "roleid")
	private AdminRole role;

	public boolean match(final String username, final String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the role
	 */
	public AdminRole getRole() {
		return role;
	}

	/**
	 * @param id
	 *           the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param username
	 *           the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password
	 *           the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param role
	 *           the role to set
	 */
	public void setRole(AdminRole role) {
		this.role = role;
	}
}
