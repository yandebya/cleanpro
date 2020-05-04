package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.Entite.CategorieService;

public interface CategorieServiceRepository extends JpaRepository<CategorieService, Long> {

	 @Query("SELECT COUNT(*) FROM CategorieService")
 public Long nombreCategorie();
}
