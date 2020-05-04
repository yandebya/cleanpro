package com.projet.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.ServiceRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Services;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;
import com.projet.Metier.CategorieServiceMetier;


@Controller
public class CategorieServiceControlleur {

	@Autowired
	public CategorieServiceMetier csm;
	@Autowired
	private ServiceRepository sr;
	@Autowired
	private CategorieServiceRepository csr;
	@Autowired
	private UtilisateurControlleur uc;
	
	  @Value("${imageUtilisateur}")
	private String photoCat;
	@RequestMapping(value="creationCategorieService", method= RequestMethod.POST)
	public String saveCatServ(CategorieService cs, @RequestParam(name = "picture") MultipartFile file)
			throws IOException {
		if (!(file.isEmpty())) {
			cs.setPhoto_categorie(file.getOriginalFilename());
		}
		csm.saveCat(cs);
		if (!(file.isEmpty())) {
			cs.setPhoto_categorie(file.getOriginalFilename());
			file.transferTo(new File(photoCat + cs.getPhoto_categorie()));
		}

		
		return"redirect:ListeCategorieService";
	}
	
	
	
	@RequestMapping(value="/saveCategorieService", method=RequestMethod.POST)
	public String saveCatServAjax(@Valid CategorieService cs) {
	
		csm.saveCat(cs);
		return"redirect:ListeCategorieService";
	}
	@RequestMapping(value="/creerCategorieService")
	public String preparSaveCatServ(Model model, HttpServletRequest hsr) {
		CategorieService c = new CategorieService();
		model.addAttribute("categorieService", c);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return"CategorieService/FormCategorieService";
	}
	
	@RequestMapping(value="supCategorieService")
	public String supprimer(Long id_categorieServivce) {	
		csm.SupprimerCatSer(id_categorieServivce);
		return"redirect:ListeCategorieService";
	}
	
	@GetMapping(value="/editCategorieService")
	 @ResponseBody
	public CategorieService editer(Long id_categorieServivce) {		
		return  csr.getOne(id_categorieServivce);
	}
	
	@RequestMapping(value="/ListeCategorieService")
	public String ListeCatServ(Model model, HttpServletRequest hsr) {
		List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("listeCategorieService", lcs);
		
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "CategorieService/ListeCategorieService";
	}
	@RequestMapping(value="/ListeCategorieParService")
	public String ListeCaPatServ(Model model, HttpServletRequest hsr,Long id_CategorieService) {
			List<Services> service = sr.ListeSerParCat(id_CategorieService);
			model.addAttribute("service", service);
			CategorieService cs = new CategorieService();
			model.addAttribute("categorieService", cs);
			Utilisateur u = uc.getLogedUserUc(hsr);
			model.addAttribute("utilisateur", u);
			model.addAttribute("yan","yandebya");
		return "Service/ListeService";
	}
	
	/*pour permettre d'aficher la liste de categorie dans le modal lors de la creation de prestataire*/
	@RequestMapping(value="/listeCategorieCP")
	public String listeCategorieAjax(Model model,HttpServletRequest hsr) {
		
		List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("listeCategorie", lcs);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		CategorieService vv = new CategorieService();
		model.addAttribute("cat", vv);
		return "prestataire/listeCategorieCP";
	}

}
