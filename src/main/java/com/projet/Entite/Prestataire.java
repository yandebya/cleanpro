package com.projet.Entite;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("prestataire")
public class Prestataire extends Utilisateur{
	
	private String piece_identite_pres;
	private String casier_judiciere_pres;
	private String niveau_etude_pres;
	private String nationalite_pres;
	private String experience_proffessionel_pres;
	private String motivation_pres;
	
	@ManyToOne
	@JoinColumn(name="idCatServ")
	private CategorieService categorieService;
	@ManyToOne
	@JoinColumn(name="idServ")
	private Services service;
	@ManyToOne
	@JoinColumn(name="idNationalite")
	private Nationalite nationalite;
	public Nationalite getNationalite() {
		return nationalite;
	}


	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}


	public Prestataire() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getPiece_identite_pres() {
		return piece_identite_pres;
	}

	public Prestataire(Long id_utilisateur, String nom_utilisateur, String prenom_utilisateur, char sexe_utilisateur,
			Date age_utilisateur, Date date_inscription, String photo_utilisateur, String username, String password,
			String email_utilisateur, String telephone_utilisateur, String code_postal_utilisateur, boolean actived,
			Set<Role> roles, String enregistrerPar, String enregistrerParPrenom, Ville ville, Quartier quartier) {
		super(id_utilisateur, nom_utilisateur, prenom_utilisateur, sexe_utilisateur, age_utilisateur, date_inscription,
				photo_utilisateur, username, password, email_utilisateur, telephone_utilisateur, code_postal_utilisateur,
				actived, roles, enregistrerPar, enregistrerParPrenom, ville, quartier);
		// TODO Auto-generated constructor stub
	}

	

	public Prestataire(Long id_utilisateur, String nom_utilisateur, String prenom_utilisateur, char sexe_utilisateur,
			Date age_utilisateur, Date date_inscription, String photo_utilisateur, String username, String password,
			String email_utilisateur, String telephone_utilisateur, String code_postal_utilisateur, boolean actived,
			Set<Role> roles, String enregistrerPar, String enregistrerParPrenom, Ville ville, Quartier quartier,
			String piece_identite_pres, String casier_judiciere_pres, String niveau_etude_pres, String nationalite_pres,
			String experience_proffessionel_pres, String motivation_pres, CategorieService categorieService,
			Services service, Nationalite nationalite) {
		super(id_utilisateur, nom_utilisateur, prenom_utilisateur, sexe_utilisateur, age_utilisateur, date_inscription,
				photo_utilisateur, username, password, email_utilisateur, telephone_utilisateur,
				code_postal_utilisateur, actived, roles, enregistrerPar, enregistrerParPrenom, ville, quartier);
		this.piece_identite_pres = piece_identite_pres;
		this.casier_judiciere_pres = casier_judiciere_pres;
		this.niveau_etude_pres = niveau_etude_pres;
		this.nationalite_pres = nationalite_pres;
		this.experience_proffessionel_pres = experience_proffessionel_pres;
		this.motivation_pres = motivation_pres;
		this.categorieService = categorieService;
		this.service = service;
		this.nationalite = nationalite;
	}


	public void setPiece_identite_pres(String piece_identite_pres) {
		this.piece_identite_pres = piece_identite_pres;
	}

	public String getCasier_judiciere_pres() {
		return casier_judiciere_pres;
	}

	public void setCasier_judiciere_pres(String casier_judiciere_pres) {
		this.casier_judiciere_pres = casier_judiciere_pres;
	}

	public String getNiveau_etude_pres() {
		return niveau_etude_pres;
	}

	public void setNiveau_etude_pres(String niveau_etude_pres) {
		this.niveau_etude_pres = niveau_etude_pres;
	}

	public String getNationalite_pres() {
		return nationalite_pres;
	}

	public void setNationalite_pres(String nationalite_pres) {
		this.nationalite_pres = nationalite_pres;
	}

	public String getExperience_proffessionel_pres() {
		return experience_proffessionel_pres;
	}

	public void setExperience_proffessionel_pres(String experience_proffessionel_pres) {
		this.experience_proffessionel_pres = experience_proffessionel_pres;
	}

	public String getMotivation_pres() {
		return motivation_pres;
	}

	public void setMotivation_pres(String motivation_pres) {
		this.motivation_pres = motivation_pres;
	}

	public CategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public Services getService() {
		return service;
	}




	public void setService(Services service) {
		this.service = service;
	}





	
	

}
