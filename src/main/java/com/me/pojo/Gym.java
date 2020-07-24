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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gymtable")
public class Gym {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gymid")
	private int gymId;
	
	@Column(name="gymname")
	private String gymName;
	
	@Column(name="gymaddress")
	private String gymAddress;
	
	@Column(name="gymdesc")
	private String gymDescription;

	
	public Gym(){
		
	}
	
	public Gym( String gymName, String gymAddress, String gymDescription){
		this.gymName = gymName;
		this.gymAddress = gymAddress;
		this.gymDescription = gymDescription;
	}
	
	
	public int getGymId() {
		return gymId;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getGymAddress() {
		return gymAddress;
	}

	public void setGymAddress(String gymAddress) {
		this.gymAddress = gymAddress;
	}

	public String getGymDescription() {
		return gymDescription;
	}

	public void setGymDescription(String gymDescription) {
		this.gymDescription = gymDescription;
	}
	
	
}

