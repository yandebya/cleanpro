package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projet.Entite.Editeur;
import com.projet.Entite.Historique;

public interface EditeurMetier {
	public Editeur creerEditeur(Editeur e, String password,Historique h,HttpServletRequest hsr);
	public List<Editeur> listeEditeur();
	
	public Page<Editeur> listeEditeurMc(String mc, Pageable pageable);
	public Page<Editeur> findEditeur(Pageable pageable);

	public Editeur editerEditeur(Editeur e, String password);
}
