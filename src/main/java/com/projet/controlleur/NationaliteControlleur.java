package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projet.Dao.NationaliteRepository;
import com.projet.Entite.Nationalite;
import com.projet.Entite.TypePrestation;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;

@Controller
public class NationaliteControlleur {

	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private NationaliteRepository nr;
	
	@RequestMapping(value="/creationNationalite", method = RequestMethod.POST)
	public String creationNationalite(Nationalite n) {
		nr.save(n);
		return "redirect:listeNationalite";
	}
	
	@RequestMapping(value="/creerNationalite")
	public String creerNationalite(Model model,HttpServletRequest hsr) {
		Nationalite n  = new Nationalite();
		model.addAttribute("nationalite", n);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "nationalite/formNationalite";
	}
	/*supprimer*/
	@RequestMapping(value="supprimerNationalite")
	public String  supprimer( Long id_nationalite,Model model) {
	nr.deleteById(id_nationalite);
	return "redirect:listeNationalite";
	}
	/*editer*/
	@RequestMapping(value="editerNationalite")
	  @ResponseBody
	private Nationalite editer( Long id_nationalite) {
	
		return nr.getOne(id_nationalite);
	}
	@RequestMapping(value="/listeNationalite")
	public String listeNationalite(Model model,HttpServletRequest hsr) {
		List<Nationalite> ln = nr.findAll();
		model.addAttribute("nationalite", ln);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "nationalite/listeNationalite";
	}
	/*pour permettre d'aficher la liste de ville dans le modal lors de la creation de quartier*/
	@RequestMapping(value="/listeNationaliteCP")
	public String listeVilleAjax(Model model,HttpServletRequest hsr) {
		
		List<Nationalite> ln = nr.findAll();
		model.addAttribute("listeNationalite", ln);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		Nationalite vv = new Nationalite();
		model.addAttribute("nat", vv);
		return "prestataire/listeNationaliteCP";
	}

}
