package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.Entite.Historique;
import com.projet.Entite.Prestataire;
import com.projet.Entite.Utilisateur;

public interface PrestataireMetier {
	public Prestataire SavePrestataire(Prestataire pres, String password);
	public Prestataire SavePrestataire(Prestataire pres, Long id_utilisateur, String password,Long id_categorieServivce,Long id_ville,Long id_nationalite,Long id_quartier,HttpServletRequest hsr);
	public Prestataire editPrestataire(Prestataire pres, String password,Long id_categorieServivce, Long id_ville, Long id_quartier);
	public Long nobrePNV();
	public void supprimerPrestataire(Long id_utilisateur);
	
	public Utilisateur GetUserPerCat(Long id_utilisateur);

	
	
	
	
}
