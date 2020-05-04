package com.projet.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.AvisClient;
import com.projet.Entite.Prestataire;

public interface AvisRepository extends JpaRepository<AvisClient, Long> {

	/*
	 * @Query("select av from AvisClient av where av.client.id_utilisateur=:xa")
	 * List<AvisClient> ListeAvisParClient(@Param("xa") Long id_utilisateur);
	 */
  
	/*@Query("select s from Utilisateur s where s.categorieService.id_categorieServivce=:x and  actived=1")
	List<Prestataire> ListePresParCat(@Param("x") Long id_CatServ);*/
  
	@Query("select av from AvisClient av where av.client.id_utilisateur=:xa")
	List<AvisClient> ListeAvisParClient(@Param("xa") Long id_utilisateur);
	
	 @Query("SELECT COUNT(*) FROM AvisClient na where id_client=:ya")
	  public Long nombreAvisParClient(@Param("ya") Long id_utilisateur);
}
