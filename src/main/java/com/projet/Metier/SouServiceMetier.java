package com.projet.Metier;

import java.util.List;

import com.projet.Entite.SousServices;

public interface SouServiceMetier {

	public SousServices creerSousServ(SousServices s, Long id_service);
	public List<SousServices> listeSousSer();
	public List<SousServices> listeSousService(Long id_service);
	
}
