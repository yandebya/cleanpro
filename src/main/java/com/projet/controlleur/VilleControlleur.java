package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projet.Dao.VilleRepository;
import com.projet.Entite.Nationalite;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;
import com.projet.Metier.VilleMetier;

@Controller
public class VilleControlleur {
	
	@Autowired
	private VilleMetier vm;
	@Autowired
	private VilleRepository vr;
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/creationVille", method = RequestMethod.POST)
	public String creationVille(@Valid Ville v,BindingResult br) {
		if(br.hasErrors()) {
			return "";
		}
		vm.creerVille(v);
		return "redirect:listeVille";
	}
	
	/*@RequestMapping(value="/creerVille")
	public String creerVille(Model model,HttpServletRequest hsr) {
		Ville v = new Ville();
		model.addAttribute("ville", v);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "ville/formVille";
	}*/
		/*supprimer*/
	@RequestMapping(value="supprimerVille")
	public String  supprimerVille( Long id_ville,Model model) {
	vr.deleteById(id_ville);
	return "redirect:listeVille";
	}
	/*editer*/
	@RequestMapping(value="editerVille")
	  @ResponseBody
	private Ville editerVille( Long id_ville) {
	
		return vr.getOne(id_ville);
	}
	
	@RequestMapping(value="/listeVille")
	public String listeVille(Model model,HttpServletRequest hsr) {
		
		List<Ville> v = vm.listeVille();
		model.addAttribute("listeVille", v);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "ville/listeVille";
	}
	/*pour permettre d'aficher la liste de ville dans le modal lors de la creation de quartier*/
	/*@RequestMapping(value="/listeVilleQaurt")
	public String listeVilleAjax(Model model,HttpServletRequest hsr) {
		
		List<Ville> v = vm.listeVille();
		model.addAttribute("listeVille", v);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		Ville vv = new Ville();
		model.addAttribute("vil", vv);
		return "quartier/Ville";
	}*/

}
