package com.projet.Dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Admin;
import com.projet.Entite.Historique;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	@Query("select c from Utilisateur c where c.nom_utilisateur like :x and type_utilisateur='admin'")
	public Page<Admin> adminParMotCle( @Param("x")String mc,Pageable pageable);
	
	
}
