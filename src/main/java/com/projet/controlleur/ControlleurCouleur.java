package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Dao.CouleurRepository;
import com.projet.Entite.EntiteCouleur;
import com.projet.Entite.Utilisateur;



@Controller
public class ControlleurCouleur {
	
	@Autowired
	private CouleurRepository cr;
	
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="creationCoul", method = RequestMethod.POST)
	public String creationC(EntiteCouleur c) {	
		cr.save(c);
		return "redirect:creerCoul";
	}
	
	@RequestMapping(value="creerCoul")
	public String creeC(Model model,HttpServletRequest hsr) {
		EntiteCouleur ec = new EntiteCouleur();
		model.addAttribute("couleur", ec);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "drapeau/formCouleur";
	}
	@RequestMapping(value="listeCoul")
	public String liste(Model model,HttpServletRequest hsr) {
		List<EntiteCouleur> lec = cr.findAll();		
		model.addAttribute("listeCouleur",lec);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "drapeau/listeCouleur";
	}

}
