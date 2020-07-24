package com.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "user_table")
@PrimaryKeyJoinColumn(name = "personID")
//@Inheritance(strategy=InheritanceType.JOINED)
public class User extends Person {

	@Column(name = "userName")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Email email;
	
	@Column(name = "role")
	private String role;
	
	@ManyToOne
	private Gym gym;

	@ManyToMany
    @JoinTable (
       name="user_clas_table",
       joinColumns = {@JoinColumn(name="personID", nullable = false, updatable = false)},
       inverseJoinColumns = {@JoinColumn(name="clasID" )}
       
    )
	private Set<ClasList> claslists = new HashSet<ClasList>();
	
	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		
	}

	public User() {
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public Set<ClasList> getClaslists() {
		return claslists;
	}

	public void setClaslists(Set<ClasList> claslists) {
		this.claslists = claslists;
	}

	
}