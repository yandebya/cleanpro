package com.projet.Metier;

import java.util.List;

import com.projet.Entite.Quartier;

public interface QuartierMetier {

	public Quartier creerQuartier(Quartier q,Long id_ville);
	public List<Quartier> listeQuartier();
}
