package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.projet.Dao.HistoriqueRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Historique;
import com.projet.Entite.Utilisateur;
@Service
public class HistoriqueMetierImp implements HistoriqueMetier{

	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private HistoriqueRepository hr;
	@Override
	public List<Historique> utilisateurHistorique( HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		return hr.ListeAdminParHistorique(utilisateur.getId_utilisateur());
	}

}
