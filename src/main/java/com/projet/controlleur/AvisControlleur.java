package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Dao.AvisRepository;
import com.projet.Entite.AvisClient;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.AvisClientMetier;

@Controller
public class AvisControlleur {
	
	@Autowired
	private AvisRepository ar;
	@Autowired
	private AvisClientMetier am;
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/creationAvisSite", method = RequestMethod.POST)
	public String creationAvis(AvisClient ac,Long id_utilisateur, HttpServletRequest hsr, Model model) {
		
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur",u);
		am.saveAvisClient(ac, hsr);
		List<AvisClient> lac = ar.ListeAvisParClient(id_utilisateur);
		model.addAttribute("listeAvisClient", lac);
		return "redirect:listeAvis";
	}
	@RequestMapping(value="/creerAvisSite")
	public String creerAvis(Model model, HttpServletRequest hsr,Long id_utilisateur ) {
		
		AvisClient avc = new AvisClient();
		model.addAttribute("avisCLient", avc);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur",u);
		List<AvisClient> lac = ar.ListeAvisParClient(id_utilisateur);
		model.addAttribute("listeAvisClient", lac);
		return "avisClient/formAvisClient";
	}
	@RequestMapping(value="/listeAvis")
	public String liste(AvisClient ac, Model model, HttpServletRequest hsr,Long id_utilisateur) {
		List<AvisClient> lac = am.listeAvis(hsr);
		model.addAttribute("listeAvisClient", lac);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur",u);
		return "avisClient/listeAvisClient";
	}
	@RequestMapping(value="/avis")
	public String avis(AvisClient ac, Model model, HttpServletRequest hsr,Long id_utilisateur) {
		List<AvisClient> lac = ar.findAll();
		model.addAttribute("listeAvisClient", lac);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur",u);
		return "avisClient/listeAvisClient";
	}
	/*supprimer*/
	@RequestMapping(value="supprimerAvis")
	public String  supprimerAvis( Long id_Avis, Model model) {
		ar.deleteById(id_Avis);
	return "redirect:listeAvis";
	}
	@RequestMapping(value="supprimerAvisPE")
	public String  supprimerAvisParEditeur( Long id_Avis, Model model) {
		ar.deleteById(id_Avis);
	return "redirect:avis";
	}
}
