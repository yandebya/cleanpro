package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.Entite.EntiteCouleur;

public interface CouleurRepository extends JpaRepository<EntiteCouleur , Long> {

}
