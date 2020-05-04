package com.projet.Entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="PROFIL")
public class Role implements Serializable{
@Id
@GeneratedValue(strategy =GenerationType.AUTO)
	private Long idRole;
	private String role;
	private String description;
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(Long idRole, String role, String description) {
		super();
		this.idRole= idRole;
		this.role = role;
		this.description = description;
	}
	
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
