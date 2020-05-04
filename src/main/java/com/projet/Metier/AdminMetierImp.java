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
import com.projet.Dao.AdminRepository;
import com.projet.Dao.HistoriqueRepository;
import com.projet.Dao.RoleRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Admin;
import com.projet.Entite.Historique;
import com.projet.Entite.Role;
import com.projet.Entite.Utilisateur;

@Service
public class AdminMetierImp implements AdminMetier {
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private HistoriqueRepository historiqueRepository;
	
	@Override
	public Admin creerAdmin(Admin a,Historique h,String password,HttpServletRequest hsr) {
		Role r = roleRepository.findByRole("ADMIN");
		a.setPassword(new BCryptPasswordEncoder().encode(a.getPassword()));
		a.setRoles(new HashSet<Role> (Arrays.asList(r)));
		a.setDate_inscription(new Date());
		
		h.setLibelle_historique("enregistrement administrateur");
		h.setDate_historique(new Date());
		
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		h.setUtilisateur(utilisateur);
		
		h.setAUteur_nom(utilisateur.getNom_utilisateur());
		h.setAuteur_prenom(utilisateur.getPrenom_utilisateur());
		h.setConcerner_nom(a.getNom_utilisateur());
		h.setConcerner_prenom(a.getPrenom_utilisateur());
		
		historiqueRepository.save(h);
		String s = a.getPhoto_utilisateur();
			a.setPhoto_utilisateur("user.jpg");
			a.setActived(true);
			
			
		
		return adminRepository.save(a);
	}

	@Override
	public Admin getAdmin(Long id_utilisateur) {
		return adminRepository.getOne(id_utilisateur);
	}


}
