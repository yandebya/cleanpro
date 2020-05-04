package com.projet.Metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.projet.Entite.Partenaire;

public interface PartenaireMetier {

	public Partenaire creerPartenaire(Partenaire e, String password);
	public List<Partenaire> listePartenaire();
	
	public Page<Partenaire> listePartenaireMc(String mc, Pageable pageable);
	public Page<Partenaire> findPartenaire(Pageable pageable);
}
