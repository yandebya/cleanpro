package com.projet.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.Entite.TypePrestation;
import com.projet.Entite.TypePrestation;

public interface TypePrestationRepository extends JpaRepository<TypePrestation, Long>{
	
	@Query("select s from TypePrestation s where s.libelleP='Prestation unique' ")
	  public List<TypePrestation> prestUnique();
	@Query("select s from TypePrestation s where s.libelleP='Prestation reguliere' ")
	  public List<TypePrestation> prestReguliere();

}
