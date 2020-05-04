package com.projet.Entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_utilisateur")
public  class Utilisateur implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_utilisateur;
	private String nom_utilisateur;
	private String prenom_utilisateur;
	private char sexe_utilisateur;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date age_utilisateur;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_inscription;
	private String photo_utilisateur;
	@Size(min=5, max=12)
	private String username;
	private String password;
	private String email_utilisateur;
	private String telephone_utilisateur;
	private String code_postal_utilisateur;
	private boolean actived;
	
	private String enregistrerPar;
	private String enregistrerParPrenom;
	@ManyToMany
	@JoinTable(name="USERS_ROLES",joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;

	@ManyToOne
	@JoinColumn(name="idVille")
	private Ville ville;

	
	@ManyToOne
	@JoinColumn(name="idQuart")
	private Quartier quartier;
	
	@OneToMany(mappedBy = "utilisateurH",cascade = CascadeType.REMOVE)
	private Collection<Historique> historique;
	/*  @ManyToMany 
	  @JoinTable(name="USERS_PROFILS",joinColumns=@JoinColumn(name="utilisateur_id" ), inverseJoinColumns=@JoinColumn(name="profil_id"))
	  private Set<Profil> profil;*/
	public Utilisateur() {
		super();
		
	}
	public Utilisateur(Long id_utilisateur, String nom_utilisateur, String prenom_utilisateur, char sexe_utilisateur,
			Date age_utilisateur, Date date_inscription,
			String photo_utilisateur, String username, String password, String email_utilisateur,
			String telephone_utilisateur, String code_postal_utilisateur, boolean actived,
			Set<com.projet.Entite.Role> roles,String enregistrerPar,String enregistrerParPrenom,Ville ville,Quartier quartier) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.nom_utilisateur = nom_utilisateur;
		this.prenom_utilisateur = prenom_utilisateur;
		this.sexe_utilisateur = sexe_utilisateur;
		this.age_utilisateur = age_utilisateur;
		this.date_inscription = date_inscription;
		this.photo_utilisateur = photo_utilisateur;
		this.username = username;
		this.password = password;
		this.email_utilisateur = email_utilisateur;
		this.telephone_utilisateur = telephone_utilisateur;
		this.code_postal_utilisateur = code_postal_utilisateur;
		this.actived = actived;
		this.roles = roles;
		this.enregistrerPar= enregistrerPar;
		this.enregistrerParPrenom= enregistrerParPrenom;
		this.ville=ville;
		this.quartier=quartier;
	}
	public Long getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(Long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getNom_utilisateur() {
		return nom_utilisateur;
	}
	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}
	public String getPrenom_utilisateur() {
		return prenom_utilisateur;
	}
	public void setPrenom_utilisateur(String prenom_utilisateur) {
		this.prenom_utilisateur = prenom_utilisateur;
	}
	public char getSexe_utilisateur() {
		return sexe_utilisateur;
	}
	public void setSexe_utilisateur(char sexe_utilisateur) {
		this.sexe_utilisateur = sexe_utilisateur;
	}
	public Date getAge_utilisateur() {
		return age_utilisateur;
	}
	public void setAge_utilisateur(Date age_utilisateur) {
		this.age_utilisateur = age_utilisateur;
	}
	public String getPhoto_utilisateur() {
		return photo_utilisateur;
	}
	public void setPhoto_utilisateur(String photo_utilisateur) {
		this.photo_utilisateur = photo_utilisateur;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail_utilisateur() {
		return email_utilisateur;
	}
	public void setEmail_utilisateur(String email_utilisateur) {
		this.email_utilisateur = email_utilisateur;
	}
	public String getTelephone_utilisateur() {
		return telephone_utilisateur;
	}
	public void setTelephone_utilisateur(String telephone_utilisateur) {
		this.telephone_utilisateur = telephone_utilisateur;
	}
	public String getCode_postal_utilisateur() {
		return code_postal_utilisateur;
	}
	public void setCode_postal_utilisateur(String code_postal_utilisateur) {
		this.code_postal_utilisateur = code_postal_utilisateur;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Date getDate_inscription() {
		return date_inscription;
	}
	public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}
	public String getEnregistrerPar() {
		return enregistrerPar;
	}
	public void setEnregistrerPar(String enregistrerPar) {
		this.enregistrerPar = enregistrerPar;
	}
	public String getEnregistrerParPrenom() {
		return enregistrerParPrenom;
	}
	public void setEnregistrerParPrenom(String enregistrerParPrenom) {
		this.enregistrerParPrenom = enregistrerParPrenom;
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
	
	 
}
