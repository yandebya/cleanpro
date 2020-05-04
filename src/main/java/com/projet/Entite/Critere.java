package com.projet.Entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Critere implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_critere;
	
	private String libelle_critere;
	
	private String description_critere;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Recruteur recruteur;

	public Critere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Critere(Long id_critere, String libelle_critere,String description_critere) {
		super();
		this.id_critere = id_critere;
		this.libelle_critere = libelle_critere;
		this.description_critere= description_critere;
	}

	public Long getId_critere() {
		return id_critere;
	}

	public void setId_critere(Long id_critere) {
		this.id_critere = id_critere;
	}

	public String getLibelle_critere() {
		return libelle_critere;
	}

	public void setLibelle_critere(String libelle_critere) {
		this.libelle_critere = libelle_critere;
	}

	public Recruteur getRecruteur() {
		return recruteur;
	}

	public void setRecruteur(Recruteur recruteur) {
		this.recruteur = recruteur;
	}

	public String getDescription_critere() {
		return description_critere;
	}

	public void setDescription_critere(String description_critere) {
		this.description_critere = description_critere;
	}
	
	

}
