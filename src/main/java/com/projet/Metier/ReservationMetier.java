package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.Entite.Reservation;

public interface ReservationMetier {

	public Reservation creerReservation(Reservation r,Long id_ville,Long id_quartier,Long id_utilisateur,Long id_categorieServivce,Long id_service,HttpServletRequest hsr);
	public Reservation creerReservationUnique(Reservation r,Long id_ville,Long id_quartier,Long id_utilisateur,Long id_categorieServivce,Long id_service,HttpServletRequest hsr);
	public List<Reservation> listeReservation();
	public List<Reservation> reservClient(HttpServletRequest hsr);
	public List<Reservation> reservPrestataire(HttpServletRequest hsr);
	public List<Reservation> derReservClient(HttpServletRequest hsr);
	public List<Reservation> derReservPrestataire(HttpServletRequest hsr);
	public void activationReservation(Long id_reservation,HttpServletRequest hsr);
}
