package com.projet.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Historique;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
	@Query("select adm from Historique adm where adm.utilisateurH.id_utilisateur=:ad")
	List<Historique> ListeAdminParHistorique(@Param("ad") Long id_utilisateur);
	
	/*@Query("select av from AvisClient av where av.client.id_utilisateur=:xa")
	List<AvisClient> ListeAvisParClient(@Param("xa") Long id_utilisateur);
	*/
}
