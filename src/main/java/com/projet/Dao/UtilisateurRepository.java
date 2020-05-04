package com.projet.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

   @Query("select s from Utilisateur s where s.username=:x")
	public Utilisateur sessionEnCours(@Param("x") String username);
   
	/*
	 * @Query("SELECT p FROM Utilisateur p WHERE p.actived=:true and p.type_utilisateur=:partenaire"
	 * ) public List<Utilisateur> listUtilActif();
	 */
   /*@Query("select count(*)  from pg_stat_activity")
   public Long utilisateurConnecte(); */
   
}
