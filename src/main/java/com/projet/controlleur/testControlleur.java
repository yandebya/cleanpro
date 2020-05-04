package com.projet.controlleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.EditeurRepository;
import com.projet.Dao.MessageRepository;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.RecruteurRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Dao.VilleRepository;
import com.projet.Entite.Utilisateur;

@Controller
public class testControlleur {
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private  PrestataireRepository pr;
	@Autowired
	private  ClientControlleur cc;
	@Autowired
	private PartenaireControlleur parr;
	@Autowired
	private PrestataireControlleur pc;
	@Autowired
	private MessageRepository mr;
	@Autowired
	private EditeurRepository er;
	@Autowired
	private VilleRepository vr;
	@Autowired
	private CategorieServiceRepository csr;
	@Autowired
	private RecruteurRepository recRep;
	@Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})

	public String test() {
		return "test/test";
	}
	@RequestMapping(value="/inscription")
	public String inscription() {
		return "prestataire/inscription";
	}
	
	@Secured(value={"ROLE_PARTENAIRE"})
@RequestMapping(value="/utilisateur")
public String utilisateur() {
	return "utilisateur/utilisateur";
}
@RequestMapping(value="/enregistrement")
public String enregistrement() {
	return "utilisateur/enregistrement";
}

@RequestMapping(value="/creation")
public String creation() {
	return "utilisateur/creation";
}
@RequestMapping(value="/client")
public String client() {
	return "utilisateur/client";
}
@RequestMapping(value="/prestataire")
public String prestataire() {
	return "utilisateur/prestataire";
}
@RequestMapping(value="/choix")
public String choix() {
	return "utilisateur/choix";
}
@RequestMapping(value="/connexion")
public String connexion() {
	return "utilisateur/connexion";
}

@Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
@RequestMapping(value="/")
public String getLogedUser(HttpServletRequest hsr,Model model,Long id_utilisateur){ 
	Utilisateur u = uc.getLogedUserUc(hsr);
	model.addAttribute("utilisateur", u);
	Long nbrePNV = pr.nrePNV();
	Long ndrePV = pr.nrePV();
	model.addAttribute("nbrePresNonVal", nbrePNV);
	Long ndrePNp = pr.nrePNp();
	model.addAttribute("ndrePNp", ndrePNp);
	model.addAttribute("nbrePresVal", ndrePV);
	Long nc= cc.nombreClient();
	model.addAttribute("nombre", nc);
	Long prr = parr.nombrePartenaire();
	model.addAttribute("nombrePartenaire", prr);
	Long nprr = pc.nombrePrestataire();
	model.addAttribute("nombrePrestataire", nprr);
	
	Long sms = mr.count();
 	model.addAttribute("nombreSmsVisiteur", sms);
	Long ne = er.nreEdi();
	model.addAttribute("nombreEditeur", ne);
	
	Long recru = recRep.count();
	model.addAttribute("nombreRecruteur", recru);
	
	Long nv = vr.nombreVille();
	model.addAttribute("nombreVille", nv);
	
	Long ncsr = csr.nombreCategorie();
	model.addAttribute("nombreCategorie", ncsr);
	
	boolean b = u.isActived();
	if(b==true) {
		 return "test/test"; 
	}
	 return "site/loginSite"; 
  }

@RequestMapping(value="forbidden")
public String forbidden() {
	return "forbidden/forbidden";
}
}
