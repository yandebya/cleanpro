package com.projet.Entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_message;
	
	private String libelle_message;
	private String nom_contact;
	private String email_contact;
	private String telephone_contact;
	private String description_message;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Message(Long id_message, String libelle_message, String nom_contact, String email_contact,
			String telephone_contact, String description_message) {
		super();
		this.id_message = id_message;
		this.libelle_message = libelle_message;
		this.nom_contact = nom_contact;
		this.email_contact = email_contact;
		this.telephone_contact = telephone_contact;
		this.description_message = description_message;
	}



	public Long getId_message() {
		return id_message;
	}

	public void setId_message(Long id_message) {
		this.id_message = id_message;
	}

	public String getLibelle_message() {
		return libelle_message;
	}

	public void setLibelle_message(String libelle_message) {
		this.libelle_message = libelle_message;
	}



	public String getNom_contact() {
		return nom_contact;
	}



	public void setNom_contact(String nom_contact) {
		this.nom_contact = nom_contact;
	}



	public String getEmail_contact() {
		return email_contact;
	}



	public void setEmail_contact(String email_contact) {
		this.email_contact = email_contact;
	}



	public String getTelephone_contact() {
		return telephone_contact;
	}



	public void setTelephone_contact(String telephone_contact) {
		this.telephone_contact = telephone_contact;
	}



	public String getDescription_message() {
		return description_message;
	}



	public void setDescription_message(String description_message) {
		this.description_message = description_message;
	}
	
	
}
