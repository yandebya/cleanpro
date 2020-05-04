package com.projet.Metier;

import java.util.List;

import com.projet.Entite.TypePrestation;

public interface TypePrestationMetier {

	
	public TypePrestation enregistrerTP(TypePrestation tp,String ca);
	public List<TypePrestation> listeTypeP();
}
