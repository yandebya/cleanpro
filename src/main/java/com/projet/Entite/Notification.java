package com.projet.Entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_notification;
	private String objet;
	private String comment;
	private boolean status;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Notification(Long id_notification, String objet, String comment, boolean status) {
		super();
		this.id_notification = id_notification;
		this.objet = objet;
		this.comment = comment;
		this.status = status;
	}

	public Long getId_notification() {
		return id_notification;
	}
	public void setId_notification(Long id_notification) {
		this.id_notification = id_notification;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
