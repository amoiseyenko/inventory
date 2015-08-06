package com.aspiderngi.inventory.db.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="TBL_AGENT")
public class AgentEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="LOGIN",unique=true)
	private String login;
	
	@Column(name="PASSWORD")
	private String password;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ROLE_ID")
	private RoleEntity role;

	@OneToMany(mappedBy="agent")
	private List<CustomerNoteEntity> notes ;
	
	public AgentEntity(){}

	
	
	public AgentEntity(Long id, String firstName, String lastName,
			String login, String password, RoleEntity role,
			List<CustomerNoteEntity> notes) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.role = role;
		this.notes = notes;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public RoleEntity getRole() {
		return role;
	}



	public void setRole(RoleEntity role) {
		this.role = role;
	}



	public List<CustomerNoteEntity> getNotes() {
		return notes;
	}



	public void setNotes(List<CustomerNoteEntity> notes) {
		this.notes = notes;
	}



	@Override
	public String toString() {
		return "AgentEntity [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", login=" + login + ", password="
				+ password + ", role=" + role + ", notes=" + notes + "]";
	}

 
}