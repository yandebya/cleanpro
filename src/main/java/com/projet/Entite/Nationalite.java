package com.projet.Entite;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Nationalite implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_nationalite;
	private String libelle_nationalite;
	@OneToMany(mappedBy = "nationalite")
	Collection<Prestataire> prestataire;
	public Nationalite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nationalite(Long id_nationalite, String libelle_nationalite) {
		super();
		this.id_nationalite = id_nationalite;
		this.libelle_nationalite = libelle_nationalite;
	}
	public Long getId_nationalite() {
		return id_nationalite;
	}
	public void setId_nationalite(Long id_nationalite) {
		this.id_nationalite = id_nationalite;
	}
	public String getLibelle_nationalite() {
		return libelle_nationalite;
	}
	public void setLibelle_nationalite(String libelle_nationalite) {
		this.libelle_nationalite = libelle_nationalite;
	}
	public Collection<Prestataire> getPrestataire() {
		return prestataire;
	}
	public void setPrestataire(Collection<Prestataire> prestataire) {
		this.prestataire = prestataire;
	}

	
	
}