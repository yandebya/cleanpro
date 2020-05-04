package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Dao.ServiceRepository;
import com.projet.Dao.SousServiceRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Services;
import com.projet.Entite.SousServices;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.CategorieServiceMetier;
import com.projet.Metier.ServiceMetier;
import com.projet.Metier.SouServiceMetier;

@Controller
public class SousServiceControlleur {

	@Autowired
	private SouServiceMetier sm;
	@Autowired
	private SousServiceRepository sr;
	@Autowired
	private ServiceMetier csm;
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/creationSousService", method = RequestMethod.POST)
	public String creerService(SousServices s, Long id_service, Model model) {	
		sm.creerSousServ(s, id_service);
		
	 return "redirect:creerSousService";
	}
	
	@RequestMapping(value="creerSousService")
	public String prepaCreerSousService(Model model,HttpServletRequest hsr) {
		SousServices s = new SousServices();
		model.addAttribute("sousService",s);
		Services cs = new Services();
		List<Services> lcs = csm.listeSer();
		model.addAttribute("Serv", cs);
		model.addAttribute("Service",lcs);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
	return "sousService/FormSousService";
	}
	
	@RequestMapping(value="ListeSousService")
	public String ListeServ(Model model,HttpServletRequest hsr) {
		List<SousServices> ls = sm.listeSousSer();
		Services cs = new Services();
		model.addAttribute("serv", cs);
		model.addAttribute("SousService",ls);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "SousService/ListeSousService";
	}
	
	@RequestMapping(value="supprimerSousService")
	public String supprimerSousService(Long id_sousService) {
		sr.deleteById(id_sousService);
		 return "redirect:ListeSousService";
	}
}
