package com.projet.Entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Abonne implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_abonne;
	private String email_abonne;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_abonne;
	public Abonne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Abonne(Long id_abonne, String email_abonne, Date date_abonne) {
		super();
		this.id_abonne = id_abonne;
		this.email_abonne = email_abonne;
		this.date_abonne = date_abonne;
	}
	public Long getId_abonne() {
		return id_abonne;
	}
	public void setId_abonne(Long id_abonne) {
		this.id_abonne = id_abonne;
	}
	public String getEmail_abonne() {
		return email_abonne;
	}
	public void setEmail_abonne(String email_abonne) {
		this.email_abonne = email_abonne;
	}
	public Date getDate_abonne() {
		return date_abonne;
	}
	public void setDate_abonne(Date date_abonne) {
		this.date_abonne = date_abonne;
	}
	
	
	
}
