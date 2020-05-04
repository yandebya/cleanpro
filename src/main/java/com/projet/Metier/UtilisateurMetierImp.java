package com.projet.Metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.projet.Dao.ClientDao;
import com.projet.Dao.HistoriqueRepository;
import com.projet.Dao.PartenaireRepository;

import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Client;
import com.projet.Entite.Historique;
import com.projet.Entite.Partenaire;
import com.projet.Entite.Utilisateur;


@Service
public class UtilisateurMetierImp implements UtilisateurMetier {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private PartenaireRepository partenaireRepository;
	@Autowired
	private ClientDao cr;
	@Autowired
	private HistoriqueRepository historiqueRepository;
	@Override
	public Partenaire saveP(Partenaire p, String motDePasse) {
		
		p.setPassword(new BCryptPasswordEncoder().encode(p.getPassword()));
	//	Profil profil = profilRepository.getOne(idProfil);
		//p.setProfil(new HashSet<Profil> (Arrays.asList(profil)));
		return partenaireRepository.save(p);
	}

	@Override
	public Partenaire savePsanProfil(Partenaire p) {
		//p.setPassword("{noop}"+motDePasse);
		return partenaireRepository.save(p);
	}

	@Override
	public Utilisateur SessionU(String username) {
		
		return utilisateurRepository.sessionEnCours(username);
	}

	@Override
	public Utilisateur GetUser(Long id_utilisateur) {
		
		return utilisateurRepository.getOne(id_utilisateur);
	}
	
	@Override
	public void activation(Long id_utilisateur,Historique h,HttpServletRequest hsr) {
		
		Utilisateur u = utilisateurRepository.getOne(id_utilisateur);
		boolean bo =u.isActived();
		
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.sessionEnCours(username);
		
		
		if(bo==true) {
			bo=false;
			h.setLibelle_historique("Desactiver Prestataire");
			h.setDate_historique(new Date());
			h.setAUteur_nom(utilisateur.getNom_utilisateur());
			h.setAuteur_prenom(utilisateur.getPrenom_utilisateur());
			h.setConcerner_nom(u.getNom_utilisateur());
			h.setConcerner_prenom(u.getPrenom_utilisateur());
			h.setUtilisateur(utilisateur);
			historiqueRepository.save(h);
		}
		else {
			bo=true;
			h.setLibelle_historique("Activer Prestataire");
			h.setDate_historique(new Date());
			h.setAUteur_nom(utilisateur.getNom_utilisateur());
			h.setAuteur_prenom(utilisateur.getPrenom_utilisateur());
			h.setConcerner_nom(u.getNom_utilisateur());
			h.setConcerner_prenom(u.getPrenom_utilisateur());
			h.setUtilisateur(utilisateur);
			historiqueRepository.save(h);
		}
		u.setActived(bo);
		
		
		
		utilisateurRepository.save(u);
	}


	
	

}
