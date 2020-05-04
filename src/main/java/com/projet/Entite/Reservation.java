package com.projet.Entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Reservation implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_reservation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_debut;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_fin;
	 @ManyToMany
	@JoinTable(name="RESERV_SERVICE",joinColumns=@JoinColumn(name="reserv_id"), inverseJoinColumns=@JoinColumn(name="serv_id"))
	private Set<Services> service;
	 @ManyToOne
		@JoinColumn(name="idVille")
		private Ville ville;
		@ManyToOne
		@JoinColumn(name="idQuart")
		private Quartier quartier;
		@ManyToOne
		@JoinColumn(name = "id_com")
		private Comission commission;
		@ManyToOne
		@JoinColumn(name="idUser")
		private Utilisateur utilisateur;
		@ManyToOne
		@JoinColumn(name="id_client")
		private Client client;
		@ManyToOne
		@JoinColumn(name="id_prestataire")
		private Prestataire prestataire;
		private Date dateReservation;
		@ManyToOne
		@JoinColumn(name="idCatServ")
		private CategorieService categorieService;
		private boolean actived;
		private String type;
		
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reservation(Long id_reservation, Set<Services> service) {
		super();
		this.id_reservation = id_reservation;
		this.service = service;
	}


	

	public Reservation(Long id_reservation, Date date_debut, Date date_fin, Set<Services> service, Ville ville,
			Quartier quartier, Comission commission, Utilisateur utilisateur, Client client,Prestataire prestataire,CategorieService categorieService,boolean actived,String type) {
		super();
		this.id_reservation = id_reservation;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.service = service;
		this.ville = ville;
		this.quartier = quartier;
		this.commission = commission;
		this.utilisateur = utilisateur;
		this.client = client;
		this.prestataire = prestataire;
		this.categorieService = categorieService;
		this.actived = actived;
		this.type=type;
	}


	public Long getId_reservation() {
		return id_reservation;
	}


	public void setId_reservation(Long id_reservation) {
		this.id_reservation = id_reservation;
	}


	public Set<Services> getService() {
		return service;
	}


	public void setService(Set<Services> service) {
		this.service = service;
	}


	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}


	public Quartier getQuartier() {
		return quartier;
	}


	public void setQuartier(Quartier quartier) {
		this.quartier = quartier;
	}


	public Date getDate_debut() {
		return date_debut;
	}


	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}


	public Date getDate_fin() {
		return date_fin;
	}


	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}


	public Comission getCommission() {
		return commission;
	}


	public void setCommission(Comission commission) {
		this.commission = commission;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Date getDateReservation() {
		return dateReservation;
	}


	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}


	public Prestataire getPrestataire() {
		return prestataire;
	}


	public void setPrestataire(Prestataire prestataire) {
		this.prestataire = prestataire;
	}


	public CategorieService getCategorieService() {
		return categorieService;
	}


	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}


	public boolean isActived() {
		return actived;
	}


	public void setActived(boolean actived) {
		this.actived = actived;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	

}
