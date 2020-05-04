package com.projet.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Dao.AbonneRepository;
import com.projet.Dao.AvisRepository;
import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.ClientDao;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.ServiceRepository;
import com.projet.Dao.TypePrestationRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Dao.VilleRepository;
import com.projet.Entite.Abonne;
import com.projet.Entite.AvisClient;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Client;
import com.projet.Entite.Message;
import com.projet.Entite.Prestataire;
import com.projet.Entite.Services;
import com.projet.Entite.TypePrestation;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;
import com.projet.Metier.CategorieServiceMetier;
import com.projet.Metier.ClientMetier;
import com.projet.Metier.MessageMetier;
import com.projet.Metier.UtilisateurMetier;

@Controller
public class SiteControlleur {
	@Autowired
	private MessageMetier mm;
	@Autowired
	private ServiceRepository sr;
	@Autowired
	public CategorieServiceMetier csm;
	@Autowired
	private ClientMetier clientMetier;
	@Autowired
	private ClientDao clientRepository;
	@Autowired
	private UtilisateurMetier um;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	UtilisateurControlleur uc;
	@Autowired
	VilleRepository vr;
	@Autowired
	private AvisRepository ar;
	@Autowired
	private TypePrestationRepository tpr;
	@Autowired
	private CategorieServiceRepository csr;
	@Autowired
	private PrestataireRepository pr;
	@Autowired
	private AbonneRepository abr;
	@Value("${imageUtilisateur}")
	private String photoAvis;
	
	@Value("${imageUtilisateur}")
	private String photoUtil;
	@Value("${imageUtilisateur}")
	private String photoCat;

	@RequestMapping(value = "/creationClient", method = RequestMethod.POST)
	public String SaveC(Client cl, String password, Model model,
			@RequestParam(name = "picture") MultipartFile file,HttpServletRequest hsr)
			throws IOException {
		if (!(file.isEmpty())) {
			cl.setPhoto_utilisateur(file.getOriginalFilename());
		}
		
		model.addAttribute("message", "utilisateur inscrit avec succes");
		clientMetier.saveClient(cl, password,hsr);
		if (!(file.isEmpty())) {
			cl.setPhoto_utilisateur(file.getOriginalFilename());
			file.transferTo(new File(photoUtil + cl.getPhoto_utilisateur()));
		}
		
		return "redirect:formSite";
	}

	@RequestMapping(value = "/getPhoto_utilisateur", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_utilisateur(String photo_utilisateur) throws IOException {
		File f = new File(photoUtil + photo_utilisateur);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value = "/getPhoto_categorie", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_categorie(String photo_categorie) throws IOException {
		File f = new File(photoCat + photo_categorie);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value = "/formSite")
	public String SaveCl(Model model) {
		Client c = new Client();
		Utilisateur u = new Utilisateur();
		model.addAttribute("client", c);
		model.addAttribute("utilisateur", u);
		
		return "site/FormClient";
	}
	@RequestMapping(value="/nombreTab")
	public String nombreTab(Long id_categorieService,Model model ) {
		CategorieService cs = csr.getOne(id_categorieService);
		
		model.addAttribute("tabCatSer", cs);
		model.addAttribute("tab", "#service-tab");
		model.addAttribute("tab2", "service-tab");
	return "redirect:indexSite";
	}
	
	@RequestMapping(value="/creationMessageSite", method = RequestMethod.POST)
	public String creationSmS(Message mes) {
		mm.creerMessage(mes);
		return "redirect:indexSite";
	}
	@RequestMapping(value = "/getPhoto_avis", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_avis(String photo_avis) throws IOException {
		File f = new File(photoUtil + photo_avis);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value="indexSite")
	public String site(Model model, HttpServletRequest hsr) {
		
		List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("ListeCategorieParServiceSite", lcs);
		
		Long  v = vr.count();
		model.addAttribute("nombreVille", v);
		
		
		List<TypePrestation> ltp = tpr.findAll();
		model.addAttribute("typeP", ltp);
		
		Message m = new Message();
		model.addAttribute("message", m);
		
		List<AvisClient> lac = ar.findAll();
		model.addAttribute("listeAvisClient", lac);
		Long nombreAc = ar.count();
		model.addAttribute("nombreAc", nombreAc);
		
		Long ndrePV = pr.nrePV();
		model.addAttribute("ndrePVs", ndrePV);
		
		/*Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		*/
		List<Prestataire> lp = pr.findAll();
		model.addAttribute("prestataire", lp);
		
		
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		
		return "index";
	}


	
	@RequestMapping(value="/produit")
	public String produit() {
		return "site/produit";
	}
	@RequestMapping(value="/blog")
	public String blog() {
		return "site/blog";
	}
	@RequestMapping(value="/serviceSite")
	public String service(Model model) {
		List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("ListeCategorieParServiceSite", lcs);
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "Site/service";
	}
	@RequestMapping(value="/ListeCategorieParServiceSite")
	public String ListeCaPatServ(Model model, HttpServletRequest hsr,Long id_CategorieService) {
			List<Services> service = sr.ListeSerParCat(id_CategorieService);
			model.addAttribute("service", service);
			//Utilisateur u = uc.getLogedUserUc(hsr);
			//model.addAttribute("utilisateur", u);
			model.addAttribute("yan","yandebya");
			CategorieService cs = csm.obtenirIdCatSer(id_CategorieService);
			model.addAttribute("servCat",cs);
		return "Site/SousService";
	}
	
	@RequestMapping(value="/sousService")
	public String sousService(Model model) {
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "site/sousService";
	}
	@RequestMapping(value="/apropos")
	public String apropos(Model model) {
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "site/apropos";
	}
	@RequestMapping(value="/team")
	public String team(Model model) {
		List<Prestataire> lp = pr.findAll();
		model.addAttribute("prestataire", lp);
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "site/team";
	}
	@RequestMapping(value="/loginSite")
	public String login() {
		return "site/login2";
	}
	@RequestMapping(value="/Statement")
	public String Statement(Model model) {
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "site/Statement";
	}
	@RequestMapping(value="/loginSite2")
	public String login2() {
		return "Site/login2";
	}
	@RequestMapping(value="/fonctionnement")
	public String fonctionnement(Model model) {
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "site/fonctionnement";
	}

	@RequestMapping(value="/creationAbonne",method = RequestMethod.POST)
	public String creationAb(Abonne a) {
		a.setDate_abonne(new Date());
		abr.save(a);
		return "redirect:indexSite";
	}
	/*@RequestMapping(value="/creerAbonne")
	public String creerAb(Model model) {
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "index";
	}*/
}
