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

import com.projet.Dao.EditeurRepository;
import com.projet.Dao.HistoriqueRepository;
import com.projet.Dao.RoleRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Editeur;
import com.projet.Entite.Historique;
import com.projet.Entite.Role;
import com.projet.Entite.Utilisateur;

@Service
public class EditeurMetierImp implements EditeurMetier{
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private EditeurRepository editeurRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;
	
	@Override
	public Editeur creerEditeur(Editeur e, String password,Historique h,HttpServletRequest hsr) {
		Role r = roleRepository.findByRole("EDITEUR");
		e.setRoles(new HashSet<Role> (Arrays.asList(r)));
		e.setPassword(new BCryptPasswordEncoder().encode(e.getPassword()));
		e.setDate_inscription(new Date());
		
		
		h.setLibelle_historique("enregistrement Editeur");
		h.setDate_historique(new Date());
		
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		h.setUtilisateur(utilisateur);
		
		h.setAUteur_nom(utilisateur.getNom_utilisateur());
		h.setAuteur_prenom(utilisateur.getPrenom_utilisateur());
		h.setConcerner_nom(e.getNom_utilisateur());
		h.setConcerner_prenom(e.getPrenom_utilisateur());
		
		historiqueRepository.save(h);
		
		
		e.setPhoto_utilisateur("téléchargement.jpg");
		e.setActived(true);
		return editeurRepository.save(e);
	}

	@Override
	public List<Editeur> listeEditeur() {

		return null;
	}

	@Override
	public Page<Editeur> listeEditeurMc(String mc, Pageable pageable) {
		return editeurRepository.editeurParMotCle(mc, pageable);
	}

	@Override
	public Page<Editeur> findEditeur(Pageable pageable) {
		return editeurRepository.findAll(pageable);
	}

	@Override
	public Editeur editerEditeur(Editeur e, String password) {
		Role r = roleRepository.findByRole("EDITEUR");
		e.setRoles(new HashSet<Role> (Arrays.asList(r)));
		e.setPassword(new BCryptPasswordEncoder().encode(e.getPassword()));
	
		e.setActived(true);
		return editeurRepository.save(e);
	}

}
