package com.projet.controlleur;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projet.Entite.Utilisateur;

@Controller
public class PaiementControlleur {
	
	@Autowired
	private UtilisateurControlleur uc;
	@RequestMapping(value="/paiement")
	public String paiement(Model model, HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "paiement/paiement";
	}

}
