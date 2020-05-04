package com.projet.Entite;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Quartier implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_quartier;
	private String libelle_quartier;
	
	@ManyToOne
	@JoinColumn(name="idVille")
	private Ville ville;
	@OneToMany(mappedBy = "quartier")
	private Collection<Reservation> reservation;
	
	@OneToMany(mappedBy = "quartier")
	private Collection<Prestataire> Utilisateur;
	public Quartier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Quartier(Long id_quartier, String libelle_quartier) {
	
		this.id_quartier = id_quartier;
		this.libelle_quartier = libelle_quartier;
	
	}
	
	public Quartier(Long id_quartier, String libelle_quartier, Ville ville) {
		super();
		this.id_quartier = id_quartier;
		this.libelle_quartier = libelle_quartier;
		this.ville = ville;
	}
	
	public Long getId_quartier() {
		return id_quartier;
	}
	public void setId_quartier(Long id_quartier) {
		this.id_quartier = id_quartier;
	}
	public String getLibelle_quartier() {
		return libelle_quartier;
	}
	public void setLibelle_quartier(String libelle_quartier) {
		this.libelle_quartier = libelle_quartier;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public Collection<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(Collection<Reservation> reservation) {
		this.reservation = reservation;
	}
	public Collection<Prestataire> getUtilisateur() {
		return Utilisateur;
	}
	public void setUtilisateur(Collection<Prestataire> utilisateur) {
		Utilisateur = utilisateur;
	}

}
