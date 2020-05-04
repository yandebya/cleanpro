package com.projet.Metier;

import java.util.List;

import com.projet.Entite.Services;

public interface ServiceMetier {

	public Services creerServ(Services s, Long id_categorieServivce);
	public List<Services> listeSer();
	public List<Services> listeService(Long id_categorie);
	
}
