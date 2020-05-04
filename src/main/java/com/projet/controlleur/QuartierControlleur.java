package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projet.Dao.QuartierRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Quartier;
import com.projet.Entite.Services;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;
import com.projet.Metier.CategorieServiceMetier;
import com.projet.Metier.QuartierMetier;
import com.projet.Metier.ServiceMetier;
import com.projet.Metier.VilleMetier;

@Controller
public class QuartierControlleur {

	@Autowired
	private QuartierMetier qm;
	@Autowired
	private QuartierRepository qr;
	@Autowired
	private VilleMetier vm;
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/creationQuartier", method = RequestMethod.POST)
	public String creerQuartier(Quartier s, Long id_ville) {	
		qm.creerQuartier(s,id_ville);
	 return "redirect:ListeQuartier";
	}
	
	@RequestMapping(value="creerQuartier")
	public String prepaCreerQuartier(Model model,HttpServletRequest hsr) {
		Quartier s = new Quartier();
		model.addAttribute("quartier",s);
		Ville v = new Ville();
		List<Ville> lcs = vm.listeVille();
		model.addAttribute("ville", v);
		model.addAttribute("listeVille",lcs);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
	return "quartier/Ville";
	}
	
	/*supprimer*/
	@RequestMapping(value="supprimerQuartier")
	public String  supprimerVille( Long id_quartier,Model model) {
	qr.deleteById(id_quartier);
	return "redirect:listeVille";
	}
	/*editer*/
	@RequestMapping(value="editerQuartier")
	  @ResponseBody
	private Quartier editerVille( Long id_quartier) {
	
		return qr.getOne(id_quartier);
	}
	
	@RequestMapping(value="ListeQuartier")
	public String ListeQuartier(Model model,HttpServletRequest hsr, Long id_ville) {
		List<Quartier> ls = qm.listeQuartier();
		List<Ville> lcs = vm.listeVille();
		Ville ville = new Ville();
		model.addAttribute("vil", ville);
		model.addAttribute("quartier",ls);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		//List<Quartier> quartier = qr.quartier(id_ville);
		model.addAttribute("listeVille", lcs);
		return "quartier/listeQuartier";
	}
	@RequestMapping(value="ListeQuartierVille")
	public String ListeQuartierVille(Model model,HttpServletRequest hsr, Long id_ville) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Quartier> quartier = qr.quartier(id_ville);
		model.addAttribute("quartier", quartier);
		Quartier q = new Quartier();
		model.addAttribute("cquartier", q);
		return "prestataire/Quartier";
	}
}
