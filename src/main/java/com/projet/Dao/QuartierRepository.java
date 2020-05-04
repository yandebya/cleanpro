package com.projet.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.Quartier;

public interface QuartierRepository extends JpaRepository<Quartier, Long>{
	@Query("select p from Quartier p where p.ville.id_ville=:x")
	public List<Quartier> quartier(@Param("x")Long id_ville);

}
