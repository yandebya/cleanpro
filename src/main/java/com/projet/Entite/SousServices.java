package com.projet.Entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SousServices implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_sousService;
	private String libelle_sousService;
	private String description_sousService;
	@ManyToOne
	@JoinColumn(name="idServ")
	private Services services;
	public SousServices() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SousServices(Long id_sousService, String libelle_sousService, String description_sousService,
			Services services) {
		super();
		this.id_sousService = id_sousService;
		this.libelle_sousService = libelle_sousService;
		this.description_sousService = description_sousService;
		this.services = services;
	}
	public Long getId_sousService() {
		return id_sousService;
	}
	public void setId_sousService(Long id_sousService) {
		this.id_sousService = id_sousService;
	}
	public String getLibelle_sousService() {
		return libelle_sousService;
	}
	public void setLibelle_sousService(String libelle_sousService) {
		this.libelle_sousService = libelle_sousService;
	}
	public String getDescription_sousService() {
		return description_sousService;
	}
	public void setDescription_sousService(String description_sousService) {
		this.description_sousService = description_sousService;
	}
	public Services getServices() {
		return services;
	}
	public void setServices(Services services) {
		this.services = services;
	}
	
	
}
