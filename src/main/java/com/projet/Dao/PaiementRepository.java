package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.Entite.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}
