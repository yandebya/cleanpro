package com.projet.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.projet.Entite.Editeur;

public interface EditeurRepository extends JpaRepository<Editeur, Long> {
 @Query("select c from Utilisateur c where c.nom_utilisateur like :x  and type_utilisateur='editeur'")
	public Page<Editeur> editeurParMotCle( @Param("x")String mc,Pageable pageable);
 @Query("SELECT COUNT(*) FROM Utilisateur v where type_utilisateur='editeur'")
 public Long nreEdi();
}
