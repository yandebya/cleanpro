package com.projet.controlleur;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Dao.CouleurRepository;
import com.projet.Dao.DrapeauRepository;
import com.projet.Entite.EntiteCouleur;
import com.projet.Entite.EntiteDrapeau;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.DrapeauMetier;


@Controller
public class ControlleurDrapeau {

	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/drapo")
	public String drapeaux(Model model) {
		model.addAttribute("sms", "salut tout le monde");
		return "listeD";
	}

	@Autowired
	private DrapeauRepository dr;
	@Autowired
	private CouleurRepository cr;
	@Autowired
	private DrapeauMetier dm;
	
	@RequestMapping(value="creationDr", method = RequestMethod.POST)
	public String creationD(EntiteDrapeau d,EntiteCouleur ec, Long id_coul, Long id_dr ) {	
		
		
		
	dm.drap(d,id_coul);
	
		return "redirect:listeDr";
	}
	
	/*public String sve( EntiteDrapeau ed) {
		List<EntiteCouleur> liste = cr.findAll();
		if(liste !=null && liste.size()!=0) {
			for (EntiteCouleur entiteCouleur : liste) {
			//	List<String> selectedPerson = ed.getSelectedCheckBox();
			}
		}
		return "";
	}*/
	
	@RequestMapping(value="creerDr")
	public String creeD(Model model,HttpServletRequest hsr) {
		EntiteDrapeau ec = new EntiteDrapeau();
		model.addAttribute("drapeau", ec);
		List<EntiteCouleur> lec = cr.findAll();		
		model.addAttribute("listeCouleur",lec);
		EntiteCouleur ecc = new EntiteCouleur();
		model.addAttribute("cc", ecc);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "drapeau/formDrapeau";
	}
	@RequestMapping(value="listeDr")
	public String liste(Model model,HttpServletRequest hsr) {
		List<EntiteDrapeau> lec = dr.findAll();		
		model.addAttribute("listeDrapeau",lec);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "drapeau/listeDrapeau";
	}
}
