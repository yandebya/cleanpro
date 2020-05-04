package com.projet.Dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {

	 @Query("select s from Services s where s.categorieService.id_categorieServivce=:x")
	List<Services> ListeSerParCat(@Param("x") Long id_CatServ);
}
