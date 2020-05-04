package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projet.Dao.TypePrestationRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Quartier;
import com.projet.Entite.TypePrestation;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;
import com.projet.Metier.CategorieServiceMetier;
import com.projet.Metier.QuartierMetier;
import com.projet.Metier.TypePrestationMetier;
import com.projet.Metier.VilleMetier;

@Controller
public class TypePrestationControlleur {

	@Autowired
	private TypePrestationRepository tpr;
	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private VilleMetier vm;
	@Autowired
	private QuartierMetier qm;
	@Autowired
	private CategorieServiceMetier csm;
	@Autowired
	private TypePrestationMetier tpm;
	
	@RequestMapping(value="creationTypeP", method = RequestMethod.POST)
	private String creerType(TypePrestation tp, String critereA) {
		tpm.enregistrerTP(tp, critereA);
		return "redirect:listeTypeP";
	}
	
	@RequestMapping(value="creerTypeP")
	private String creationType(Model model, HttpServletRequest hsr) {
		TypePrestation typeP = new TypePrestation();
		
		model.addAttribute("typeP", typeP);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "typePrestation/formTypeP";
	}
	
	
	@RequestMapping(value="editerType")
	  @ResponseBody
	private TypePrestation editer( Long Id_typeP) {
	
		return tpr.getOne(Id_typeP);
	}
	@RequestMapping(value="supprimerType")
	public String  supprimer( Long id_typeP,Model model) {
	tpr.deleteById(id_typeP);
	return "redirect:listeTypeP";
	}
	
	@RequestMapping(value="/listeTypeP")
	private String liste(Model model,  HttpServletRequest hsr) {
		List<TypePrestation> ltp = tpr.findAll();
		model.addAttribute("typeP", ltp);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
	
		List<TypePrestation> tpre = tpr.findAll();	
		model.addAttribute("typeP", tpre);
		
		return "typePrestation/listeTypeP";
	}
	

	/*type de prestation*/
	@RequestMapping(value="/typeDePrestation")
	public String type(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<TypePrestation> ltp = tpr.prestReguliere();
		model.addAttribute("typeP", ltp);
		List<TypePrestation> ltp2 = tpr.prestUnique();
		model.addAttribute("typePU", ltp2);
		
		
		Ville v = new Ville();
		List<Ville> vil = vm.listeVille();
		model.addAttribute("ville", v);
		model.addAttribute("listeVille",vil);
		
		Quartier q = new Quartier();
		List<Quartier> qart = qm.listeQuartier();
		model.addAttribute("quartier", q);
		model.addAttribute("listeQuartier",qart);
		
		CategorieService cs = new CategorieService();
		List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("categorie", cs);
		model.addAttribute("listeCategorie", lcs);
		
		return "typePrestation/typeReservation";
	}
}
