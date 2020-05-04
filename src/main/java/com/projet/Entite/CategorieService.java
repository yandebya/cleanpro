package com.projet.Entite;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CategorieService implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_categorieServivce;
	//@NotNull(message="champ ne peut pas etre vide")
	/* @Size(min=3,max=25) */
	private String libelle_categorie;
	private String description_categorie;
	@OneToMany(mappedBy = "categorieService", cascade = CascadeType.REMOVE)
	private Collection<Services> service;
	
	@OneToMany(mappedBy = "categorieService",cascade = CascadeType.REMOVE)
	private Collection<Prestataire> prestataire;
	private String photo_categorie;
	
	public CategorieService() {
		super();
	
	}
	public CategorieService(Long id_categorieServivce, String libelle_categorie) {
		this.id_categorieServivce = id_categorieServivce;
		this.libelle_categorie = libelle_categorie;
		
	}


	public CategorieService(Long id_categorieServivce, @Size(min = 3, max = 15) String libelle_categorie,
			String description_categorie, Collection<Services> service, Collection<Prestataire> prestataire,String photo_categorie) {
		super();
		this.id_categorieServivce = id_categorieServivce;
		this.libelle_categorie = libelle_categorie;
		this.description_categorie = description_categorie;
		this.service = service;
		this.prestataire = prestataire;
		this.photo_categorie=photo_categorie;
	}


	public Long getId_categorieServivce() {
		return id_categorieServivce;
	}

	public void setId_categorieServivce(Long id_categorieServivce) {
		this.id_categorieServivce = id_categorieServivce;
	}

	public String getLibelle_categorie() {
		return libelle_categorie;
	}

	public void setLibelle_categorie(String libelle_categorie) {
		this.libelle_categorie = libelle_categorie;
	}

	public String getDescription_categorie() {
		return description_categorie;
	}

	public void setDescription_categorie(String description_categorie) {
		this.description_categorie = description_categorie;
	}

	public Collection<Services> getService() {
		return service;
	}

	public void setService(Collection<Services> service) {
		this.service = service;
	}

	public Collection<Prestataire> getPrestataire() {
		return prestataire;
	}

	public void setPrestataire(Collection<Prestataire> prestataire) {
		this.prestataire = prestataire;
	}
	public String getPhoto_categorie() {
		return photo_categorie;
	}
	public void setPhoto_categorie(String photo_categorie) {
		this.photo_categorie = photo_categorie;
	}
	
	
}
