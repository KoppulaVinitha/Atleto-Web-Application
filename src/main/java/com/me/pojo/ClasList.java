/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.me.pojo;

/**
 *
 * @author koppu
 */

import java.util.Set;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "Classes")
public class ClasList {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "clasID", unique=true, nullable = false)
	private int clasID;
	
	@Column(name = "clasname")
	private String clasName;
	
	
	@Column(name = "clasDesc")
	private String clasDesc;
	
	
	@Column(name = "uname")
	private String postedName;
	
	
	@Column(name = "uId")
	private String postedID;
	
	@Column(name = "posteddate")
	private Date postedDate;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Gym gym;
	
	@ManyToMany(mappedBy="claslists") 
	private Set<User> users;

	public ClasList(){
		
	}
	
	public ClasList(String clasname, String clasdesc){
		this.clasName = clasname;
		this.clasDesc = clasdesc;
		this.postedDate = new Date();
		
	}
	public int getClasID() {
		return clasID;
	}

	public void setClasID(int clasID) {
		this.clasID = clasID;
	}

	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getClasName() {
		return clasName;
	}

	public void setClasName(String clasName) {
		this.clasName = clasName;
	}

	public String getClasDesc() {
		return clasDesc;
	}

	public void setClasDesc(String clasDesc) {
		this.clasDesc = clasDesc;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public String getPostedName() {
		return postedName;
	}

	public void setPostedName(String postedName) {
		this.postedName = postedName;
	}

	public String getPostedID() {
		return postedID;
	}

	public void setPostedID(String postedID) {
		this.postedID = postedID;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	
	
	
}
