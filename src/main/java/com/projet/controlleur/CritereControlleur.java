package com.projet.controlleur;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Entite.Critere;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.CritereMetier;

@Controller
public class CritereControlleur {

	@Autowired
	private CritereMetier cm;
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/creationCritere", method = RequestMethod.POST)
	public String creerCritere(Critere c) {
		cm.creerCritere(c);
		return "redirect:creerCritere";
	}
	
	@RequestMapping(value="/creerCritere")
	public String creerCrite(Model model, HttpServletRequest hsr) {
		Critere cr = new Critere();
		model.addAttribute("critere", cr);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "critere/FormCritere";
	}
}
