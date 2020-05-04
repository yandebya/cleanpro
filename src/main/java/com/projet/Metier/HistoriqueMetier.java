package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.Entite.Historique;

public interface HistoriqueMetier {

	List<Historique> utilisateurHistorique(HttpServletRequest hsr);
}
