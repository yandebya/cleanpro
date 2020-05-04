package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projet.Dao.HistoriqueRepository;
import com.projet.Entite.Historique;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.HistoriqueMetier;

@Controller
public class HistoriqueControlleur {

	@Autowired
	private UtilisateurControlleur uc;

@Autowired
private HistoriqueRepository hisr;
@Autowired
private HistoriqueMetier hisrm;
	@RequestMapping(value="/historique")
	public String historique(Model model, HttpServletRequest hsr) {
		List<Historique> his = hisr.findAll();
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		model.addAttribute("historique", his);
		 return"historique/historique";
	}
	@RequestMapping(value="/historiqueUtilisateur")
	public String listeHistorique(Model model,Long id_utilisateur, HttpServletRequest hsr){
		List<Historique> his = hisrm.utilisateurHistorique(hsr);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		model.addAttribute("historique", his);
		return "historique/historique";
	}
}
