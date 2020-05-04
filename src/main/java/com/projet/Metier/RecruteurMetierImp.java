package com.projet.Metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.Dao.HistoriqueRepository;
import com.projet.Dao.RecruteurRepository;
import com.projet.Dao.RoleRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Historique;
import com.projet.Entite.Recruteur;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Role;
@Service
public class RecruteurMetierImp implements RecruteurMetier {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RecruteurRepository recruteurRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;
	@Autowired
	private UtilisateurRepository ur;
	
	@Override
	public Recruteur creerRecruteur(Recruteur rec, String password,Historique h,HttpServletRequest hsr) {
		Role r = roleRepository.findByRole("RECRUTEUR");
		rec.setRoles(new HashSet<Role> (Arrays.asList(r)));
		rec.setPassword(new BCryptPasswordEncoder().encode(rec.getPassword()));
		rec.setDate_inscription(new Date());
		
		
		h.setLibelle_historique("enregistrement recruteur");
		h.setDate_historique(new Date());
		
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		h.setUtilisateur(utilisateur);
		
		h.setAUteur_nom(utilisateur.getNom_utilisateur());
		h.setAuteur_prenom(utilisateur.getPrenom_utilisateur());
		h.setConcerner_nom(rec.getNom_utilisateur());
		h.setConcerner_prenom(rec.getPrenom_utilisateur());
		
		historiqueRepository.save(h);
		
		rec.setPhoto_utilisateur("téléchargement.jpg");
		rec.setActived(true);
		return recruteurRepository.save(rec);
	}

	@Override
	public List<Recruteur> ListeRecruteur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Recruteur> listeRecruteurMc(String mc, Pageable pageable) {
		return recruteurRepository.recruteurParMotCle(mc, pageable);
	}

	@Override
	public Page<Recruteur> findRecruteurr(Pageable pageable) {
		return recruteurRepository.findAll(pageable);
	}

	
	
}
