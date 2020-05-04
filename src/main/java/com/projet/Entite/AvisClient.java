package com.projet.Entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AvisClient implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_Avis;
	private String libelle_avis;
	private String nom_avis;
	private String prenom_avis;
	private String photo_avis;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_avis;
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;
	public AvisClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AvisClient(Long id_Avis, String libelle_avis, String nom_avis, String prenom_avis, String photo_avis,
			Date date_avis, Client client) {
		super();
		this.id_Avis = id_Avis;
		this.libelle_avis = libelle_avis;
		this.nom_avis = nom_avis;
		this.prenom_avis = prenom_avis;
		this.photo_avis = photo_avis;
		this.date_avis = date_avis;
		this.client = client;
	}

	
	public Long getId_Avis() {
		return id_Avis;
	}
	

	public void setId_Avis(Long id_Avis) {
		this.id_Avis = id_Avis;
	}
	public String getLibelle_avis() {
		return libelle_avis;
	}
	public void setLibelle_avis(String libelle_avis) {
		this.libelle_avis = libelle_avis;
	}

	public String getNom_avis() {
		return nom_avis;
	}

	public void setNom_avis(String nom_avis) {
		this.nom_avis = nom_avis;
	}

	public String getPrenom_avis() {
		return prenom_avis;
	}

	public void setPrenom_avis(String prenom_avis) {
		this.prenom_avis = prenom_avis;
	}

	public String getPhoto_avis() {
		return photo_avis;
	}

	public void setPhoto_avis(String photo_avis) {
		this.photo_avis = photo_avis;
	}

	public Date getDate_avis() {
		return date_avis;
	}

	public void setDate_avis(Date date_avis) {
		this.date_avis = date_avis;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
