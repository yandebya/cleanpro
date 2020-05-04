package com.projet.controlleur;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Dao.ClientDao;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Client;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.ClientMetier;
import com.projet.Metier.UtilisateurMetier;

@Controller
//@RequestMapping(value="/util")
public class UtilisateurControlleur {

	@Autowired
	private ClientMetier clientMetier;
	@Autowired
	private ClientDao clientRepository;
	@Autowired
	private UtilisateurMetier um;
	@Autowired
	private UtilisateurRepository ur;

	@RequestMapping(value="/login")
	public String connexion() {
		return "site/login2";
	}
	
	@Value("${imageUtilisateur}")
	private String photoUtil;
	@RequestMapping(value = "/profilClient2")
	@Secured(value={"ROLE_CLIENT"})
	public String getLogedUser(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u){ 
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		model.addAttribute("username", username);
		Utilisateur utilisateur = ur.sessionEnCours(username);
		Long cl = clientMetier.nombreClient(id_utilisateur);
		
		model.addAttribute("nombreClient", cl);
		model.addAttribute("utilisateur", utilisateur);
	  return "profil/profilClient"; 
	  }
	
	//edition d'un client
	@Secured(value={"ROLE_CLIENT"})
	@RequestMapping(value="/editionClient")
	public String editionClient(Client c,String password, @RequestParam(name = "picture") MultipartFile file,HttpServletRequest hsr) 
			throws  IOException {
		if (!(file.isEmpty())) {
			c.setPhoto_utilisateur(file.getOriginalFilename());
		}
		clientMetier.editClient(c, password,hsr);
		if (!(file.isEmpty())) {
			c.setPhoto_utilisateur(file.getOriginalFilename());
			file.transferTo(new File(photoUtil + c.getPhoto_utilisateur()));
		}
		return "redirect:profilClient2";
	}
	@Secured(value={"ROLE_CLIENT"})
	@RequestMapping(value="/editerClient")
	public String prepareEditionClient(Long id_utilisateur, Model model) {
		Utilisateur edition = um.GetUser(id_utilisateur);
		model.addAttribute("utilisateur", edition);
		return "profil/profilEdClient";
	}
	//methode a utiliser pour afficher le nom et la photo de l'utilisateur connecté
		public Utilisateur getLogedUserUc(HttpServletRequest hsr){ 
			HttpSession hs =hsr.getSession();
			SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT");
			String username=sc.getAuthentication().getName();
		  return ur.sessionEnCours(username);
		  }
}
