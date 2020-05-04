package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.Entite.Ville;

public interface VilleRepository extends JpaRepository<Ville, Long> {

	 @Query("SELECT COUNT(*) FROM Ville")
 public Long nombreVille();
}
