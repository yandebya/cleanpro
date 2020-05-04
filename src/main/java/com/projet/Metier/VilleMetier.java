package com.projet.Metier;

import java.util.List;

import com.projet.Entite.Ville;

public interface VilleMetier {

	public Ville creerVille(Ville v);
	public List<Ville> listeVille();
}
