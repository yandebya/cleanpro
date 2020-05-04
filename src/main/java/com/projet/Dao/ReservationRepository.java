package com.projet.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Reservation;
import com.projet.Entite.Utilisateur;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select e from Reservation e where e.client.id_utilisateur=:xa")
	List<Reservation> reserationClient(@Param("xa") Long id_utilisateur);
	
	@Query("select i from Reservation i where i.prestataire.id_utilisateur=:ya and actived=1" )
	List<Reservation> reserationPrestataire(@Param("ya") Long id_utilisateur);
	
	
	@Query("select r from Reservation r where r.client.id_utilisateur=:xa and r.id_reservation = (select MAX(r.id_reservation)from Reservation r  where r.client.id_utilisateur=:xa)")
	List<Reservation> dernierReserationClient(@Param("xa") Long id_utilisateur);
	
	/*
	 * @Query("select p from Reservation p where p.prestataire.id_utilisateur=:yya and p.id_reservation = (select MAX(p.id_reservation)from Reservation p  where p.prestataire.id_utilisateur=:yya) and actived=1"
	 * ) List<Reservation> dernierReserationprestataire(@Param("yya") Long
	 * id_utilisateur);
	 */
	@Query("select p from Reservation p where p.prestataire.id_utilisateur=:yya  and actived=0")
	List<Reservation> dernierReserationprestataire(@Param("yya") Long id_utilisateur);
	
	 
}
