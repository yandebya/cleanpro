package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.Entite.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

}
