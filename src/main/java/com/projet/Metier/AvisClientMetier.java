package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.Entite.AvisClient;

public interface AvisClientMetier {
	
	public AvisClient saveAvisClient(AvisClient ac,HttpServletRequest hsr );

	public List<AvisClient> listeAvis( HttpServletRequest hsr);
	
	public Long nombreAvis( HttpServletRequest hsr);
	
}
