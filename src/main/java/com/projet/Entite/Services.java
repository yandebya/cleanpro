package com.projet.Entite;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Services implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_service;
	private String libelle_service;
	private String description_service;
	@ManyToOne
	@JoinColumn(name="idCatServ")
	private CategorieService categorieService;

	@OneToMany(mappedBy = "services",cascade = CascadeType.REMOVE)
	public Collection<SousServices> sousServices;
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Services(Long id_service, String libelle_service, String description_service,
			CategorieService categorieService) {
		super();
		this.id_service = id_service;
		this.libelle_service = libelle_service;
		this.description_service = description_service;
		this.categorieService = categorieService;
	}
	public Long getId_service() {
		return id_service;
	}
	public void setId_service(Long id_service) {
		this.id_service = id_service;
	}
	public String getLibelle_service() {
		return libelle_service;
	}
	public void setLibelle_service(String libelle_service) {
		this.libelle_service = libelle_service;
	}
	public String getDescription_service() {
		return description_service;
	}
	public void setDescription_service(String description_service) {
		this.description_service = description_service;
	}
	public CategorieService getCategorieService() {
		return categorieService;
	}
	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}
	public Collection<SousServices> getSousServices() {
		return sousServices;
	}
	public void setSousServices(Collection<SousServices> sousServices) {
		this.sousServices = sousServices;
	}
	
	
}
