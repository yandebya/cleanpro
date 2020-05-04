package com.projet.Metier;

import java.util.List;

import com.projet.Entite.CategorieService;
public interface CategorieServiceMetier {

	public CategorieService saveCat(CategorieService cs);
	public List<CategorieService> liseCateServ();
	public CategorieService obtenirIdCatSer(Long id_categorieServivce);
	
	public void  SupprimerCatSer(Long id_catServ);
	
}
