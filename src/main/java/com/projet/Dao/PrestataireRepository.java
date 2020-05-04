package com.projet.Dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Prestataire;
import com.projet.Entite.Utilisateur;

public interface PrestataireRepository extends JpaRepository<Prestataire, Long> {
	
	@Query("select c from Utilisateur c where c.nom_utilisateur like :x  and type_utilisateur='prestataire' and actived=1")
	public Page<Prestataire> prestataireParMotCleActive( @Param("x")String mc,Pageable pageable);
	
	  @Query("select p from Utilisateur p where p.nom_utilisateur like :y  and type_utilisateur='prestataire' and actived=0 and code_postal_utilisateur!=null")
	  public Page<Prestataire> prestataireParMotCle( @Param("y")String mc,Pageable pageable);
	  
	  @Query("select np from Utilisateur np where np.nom_utilisateur like :ny  and type_utilisateur='prestataire' and actived=0 and code_postal_utilisateur=null")
	  public Page<Prestataire> prestataireParMotCleNonPost( @Param("ny")String mc,Pageable pageable);
	  
	  @Query("SELECT COUNT(*) FROM Utilisateur u where type_utilisateur='prestataire' and actived=0 and code_postal_utilisateur!=null")
	  public Long nrePNV();
	  @Query("SELECT COUNT(*) FROM Utilisateur u where type_utilisateur='prestataire' and actived=0 and code_postal_utilisateur=null")
	  public Long nrePNp();
	  @Query("SELECT COUNT(*) FROM Utilisateur v where type_utilisateur='prestataire' and  actived=1")
	  public Long nrePV();
	 
	  @Query("select u from Utilisateur u WHERE u.id_utilisateur=:a")
	  public Utilisateur getUserCategorie(@Param("a")Long id_utilisateur);
	  

	  @Query("delete from Utilisateur ud WHERE ud.id_utilisateur=:d")
	  public Utilisateur supprMultiple(@Param("d")Long id_utilisateur);
	  
	  @Query("select s from Utilisateur s where s.categorieService.id_categorieServivce=:x and  actived=1")
		List<Prestataire> ListePresParCat(@Param("x") Long id_CatServ);
	  
	  
}
