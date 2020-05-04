package com.projet.Metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.projet.Dao.AvisRepository;
import com.projet.Dao.ClientDao;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.AvisClient;
import com.projet.Entite.Client;
import com.projet.Entite.Role;
import com.projet.Entite.Utilisateur;

@Service
public class AvisClientMetierImp implements AvisClientMetier {

	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private AvisRepository acr;
	@Autowired
	private ClientDao cr;

	@Override
	public AvisClient saveAvisClient(AvisClient ac,HttpServletRequest hsr) {
	/*	Role r = roleRepository.findByRole("PRESTATAIRE");
		pres.setRoles(new HashSet<Role> (Arrays.asList(r)));*/
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		
		ac.setNom_avis(utilisateur.getNom_utilisateur());
		ac.setPrenom_avis(utilisateur.getPrenom_utilisateur());
		ac.setPhoto_avis(utilisateur.getPhoto_utilisateur());
		ac.setDate_avis(new Date());
		
		//Client c = cr.getOne(id_client);
		Client client  = new Client();
		client.setId_utilisateur(utilisateur.getId_utilisateur());
		//ac.setClient(new Client());
		//c.setId_utilisateur(utilisateur.getId_utilisateur());
		ac.setClient(client);
		
		
		return acr.save(ac);
	}

	
	@Override
	public Long nombreAvis( HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		
		return acr.nombreAvisParClient(utilisateur.getId_utilisateur());
	}


	@Override
	public List<AvisClient> listeAvis(HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		return acr.ListeAvisParClient(utilisateur.getId_utilisateur());
	}
}
