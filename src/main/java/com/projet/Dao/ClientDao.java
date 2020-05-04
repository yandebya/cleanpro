package com.projet.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

	@Query("select c from Utilisateur c where c.nom_utilisateur like :x  and type_utilisateur='client'")
	public Page<Client> clientParMotCle( @Param("x")String mc,Pageable pageable);
}
