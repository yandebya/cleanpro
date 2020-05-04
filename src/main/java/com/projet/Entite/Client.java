package com.projet.Entite;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("client")
public class Client extends Utilisateur {

	@OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
	private Collection<AvisClient> avisClient;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Long id_utilisateur, String nom_utilisateur, String prenom_utilisateur, char sexe_utilisateur,
			Date age_utilisateur, Date date_inscription, String photo_utilisateur, String username, String password,
			String email_utilisateur, String telephone_utilisateur, String code_postal_utilisateur, boolean actived,
			Set<Role> roles, String enregistrerPar, String enregistrerParPrenom, Ville ville, Quartier quartier) {
		super(id_utilisateur, nom_utilisateur, prenom_utilisateur, sexe_utilisateur, age_utilisateur, date_inscription,
				photo_utilisateur, username, password, email_utilisateur, telephone_utilisateur, code_postal_utilisateur,
				actived, roles, enregistrerPar, enregistrerParPrenom, ville, quartier);
		// TODO Auto-generated constructor stub
	}

	public Client(Collection<AvisClient> avisClient) {
		super();
		this.avisClient = avisClient;
	}

	public Collection<AvisClient> getAvisClient() {
		return avisClient;
	}

	public void setAvisClient(Collection<AvisClient> avisClient) {
		this.avisClient = avisClient;
	}

	
}
