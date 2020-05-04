package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projet.Entite.Historique;
import com.projet.Entite.Recruteur;

public interface RecruteurMetier {

	public Recruteur creerRecruteur(Recruteur r, String password,Historique h,HttpServletRequest hsr);
	public List<Recruteur> ListeRecruteur();
	
	public Page<Recruteur> listeRecruteurMc(String mc, Pageable pageable);
	public Page<Recruteur> findRecruteurr(Pageable pageable);
	
}
