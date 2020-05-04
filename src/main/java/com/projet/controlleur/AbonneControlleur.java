package com.projet.controlleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projet.Dao.AbonneRepository;
import com.projet.Entite.Abonne;

@Controller
public class AbonneControlleur {
	
	@Autowired
	private AbonneRepository ar;

	//@Autowired
	//private UtilisateurControlleur uc;
	
	/*@RequestMapping(value="/creationAbonne")
	public String creationAb(Abonne a) {
		ar.save(a);
		return "redirect:indexSite";
	}
	@RequestMapping(value="/creerAbonne")
	public String creerAb(Model model) {
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "index";
	}*/
	@RequestMapping(value="/listeAbonne")
	public String listeAb(Model model) {
		return "";
	}
}
