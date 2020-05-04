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

@Controller
public class ServiceControlleur {

	@Autowired
	private SousServiceRepository ssr;
	@Autowired
	private ServiceMetier sm;
	@Autowired
	private ServiceRepository sr;
	@Autowired
	private CategorieServiceMetier csm;
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/creationService", method = RequestMethod.POST)
	public String creerService(Services s, Long id_categorieServivce, Model model) {	
		sm.creerServ(s,id_categorieServivce);
		model.addAttribute("message", "Enregistrement effectuer avec succès");
	 return "redirect:ListeService";
	}
	
	@RequestMapping(value="creerService")
	public String prepaCreerService(Model model,HttpServletRequest hsr) {
		Services s = new Services();
		model.addAttribute("service",s);
		CategorieService cs = new CategorieService();
		List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("CatServ", cs);
		model.addAttribute("categorieService",lcs);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
	return "service/FormService";
	}
	
	@RequestMapping(value="ListeService")
	public String ListeServ(Model model,HttpServletRequest hsr) {
		List<Services> ls = sm.listeSer();
		CategorieService cs = new CategorieService();
		model.addAttribute("categorieService", cs);
		model.addAttribute("service",ls);
		List<CategorieService> lsr = csm.liseCateServ();
		model.addAttribute("listeCategorie",lsr);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "service/ListeService";
	}
	
	@RequestMapping(value="listeSousService")
	public String ListeServ(Model model,HttpServletRequest hsr,Long id_service) {
		List<SousServices> ls = ssr.ListeSouSerParCat(id_service);
		model.addAttribute("SousService",ls);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "sousService/ListeSousService";
	}
	
	@RequestMapping(value="supprimerService")
	public String supprimerService(Long id_service) {
		sr.deleteById(id_service);
		 return "redirect:ListeService";
	}
	
	/*@RequestMapping(value="listeServiceCat")
	public String ListeServCat(Model model,HttpServletRequest hsr,Long id_service) {
		List<CategorieService> ls = csm.liseCateServ();
		model.addAttribute("listeCategorie",ls);
		CategorieService cs = new CategorieService();
		model.addAttribute("cats", cs);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "service/Categorie";
	}*/
}
