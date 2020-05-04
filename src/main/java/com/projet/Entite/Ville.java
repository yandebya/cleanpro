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
import javax.validation.constraints.Size;

@Entity
public class Ville implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_ville;
	@Size(min=3, max=20)
	private String libelle_ville;
	
	@OneToMany(mappedBy = "ville" , cascade=CascadeType.REMOVE)
	private Collection<Quartier> quartier;
	@OneToMany(mappedBy = "ville", cascade=CascadeType.REMOVE)
	private Collection<Reservation> reservation;
	
	@OneToMany(mappedBy = "ville", cascade=CascadeType.REMOVE)
	private Collection<Utilisateur> utilisateur;

	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ville(Long id_ville, String libelle_ville) {
		this.id_ville = id_ville;
		this.libelle_ville = libelle_ville;
	}

	public Ville(Long id_ville, String libelle_ville, Collection<Quartier> quartier) {
		super();
		this.id_ville = id_ville;
		this.libelle_ville = libelle_ville;
		this.quartier = quartier;
	}

	public Long getId_ville() {
		return id_ville;
	}

	public void setId_ville(Long id_ville) {
		this.id_ville = id_ville;
	}

	public String getLibelle_ville() {
		return libelle_ville;
	}

	public void setLibelle_ville(String libelle_ville) {
		this.libelle_ville = libelle_ville;
	}

	public Collection<Quartier> getQuartier() {
		return quartier;
	}

	public void setQuartier(Collection<Quartier> quartier) {
		this.quartier = quartier;
	}

	public Collection<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(Collection<Reservation> reservation) {
		this.reservation = reservation;
	}

	public Collection<Utilisateur> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Collection<Utilisateur> utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
}
