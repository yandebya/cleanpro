package com.projet.Dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Services;
import com.projet.Entite.SousServices;

public interface SousServiceRepository extends JpaRepository<SousServices, Long> {

	 @Query("select s from SousServices s where s.services.id_service=:x")
	 /*SELECT * FROM `sous_services`  where id_serv=29*/
	
	List<SousServices> ListeSouSerParCat(@Param("x") Long id_Serv);

}
