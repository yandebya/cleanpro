package com.projet.Entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Historique implements Serializable{

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	Long id_historique;
	String libelle_historique;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurH;
	private Date date_historique;
	private String AUteur_nom;
	private String Auteur_prenom;
	private String concerner_nom;
	private String concerner_prenom;
	private String Auteur_role;
	public Historique() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Historique(Long id_historique, String libelle_historique, Utilisateur utilisateurH) {
		super();
		this.id_historique = id_historique;
		this.libelle_historique = libelle_historique;
		this.utilisateurH = utilisateurH;
	}
	public Long getId_historique() {
		return id_historique;
	}
	public void setId_historique(Long id_historique) {
		this.id_historique = id_historique;
	}
	public String getLibelle_historique() {
		return libelle_historique;
	}
	public void setLibelle_historique(String libelle_historique) {
		this.libelle_historique = libelle_historique;
	}
	public Utilisateur getUtilisateur() {
		return utilisateurH;
	}
	public void setUtilisateur(Utilisateur utilisateurH) {
		this.utilisateurH = utilisateurH;
	}
	public Date getDate_historique() {
		return date_historique;
	}
	public void setDate_historique(Date date) {
		this.date_historique = date;
	}
	public String getAUteur_nom() {
		return AUteur_nom;
	}
	public void setAUteur_nom(String aUteur_nom) {
		AUteur_nom = aUteur_nom;
	}
	public String getAuteur_prenom() {
		return Auteur_prenom;
	}
	public void setAuteur_prenom(String auteur_prenom) {
		Auteur_prenom = auteur_prenom;
	}
	public String getAuteur_role() {
		return Auteur_role;
	}
	public void setAuteur_role(String auteur_role) {
		Auteur_role = auteur_role;
	}
	public Utilisateur getUtilisateurH() {
		return utilisateurH;
	}
	public void setUtilisateurH(Utilisateur utilisateurH) {
		this.utilisateurH = utilisateurH;
	}
	public String getConcerner_nom() {
		return concerner_nom;
	}
	public void setConcerner_nom(String concerner_nom) {
		this.concerner_nom = concerner_nom;
	}
	public String getConcerner_prenom() {
		return concerner_prenom;
	}
	public void setConcerner_prenom(String concerner_prenom) {
		this.concerner_prenom = concerner_prenom;
	}
	
}
