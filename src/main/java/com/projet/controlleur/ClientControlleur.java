package com.projet.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Dao.AvisRepository;
import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.ClientDao;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Client;
import com.projet.Entite.Historique;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.AvisClientMetier;
import com.projet.Metier.ClientMetier;
import com.projet.Metier.UtilisateurMetier;

@Controller
public class ClientControlleur {

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
	private  PrestataireRepository pr;
	@Autowired
	private AvisClientMetier am;
	@Autowired
	private CategorieServiceRepository csr;
	@Value("${imageUtilisateur}")
	private String photoUtil;

	@RequestMapping(value = "/creationClientbb", method = RequestMethod.POST)
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
			file.transferTo(new File(photoUtil + cl.getId_utilisateur()));
		}
		
		return "redirect:creerClient";
	}

	@RequestMapping(value = "/getPhoto_utilisateurc", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_utilisateur(Long id_utilisateur) throws IOException {
		File f = new File(photoUtil + id_utilisateur);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value = "/creerClient")
	public String SaveCl(Model model) {
		Client c = new Client();
		Utilisateur u = new Utilisateur();
		model.addAttribute("client", c);
		model.addAttribute("utilisateur", u);
		
		return "utilisateur/FormClient";
	}

	@RequestMapping(value = "/listeClient")
	public String Liste(Model model) {
		List<Client> c = clientMetier.listeClient();
		model.addAttribute("client", c);
		return "utilisateur/ListeClient";
	}

	// supprimer un client
	@RequestMapping("/supprimerClient")
	public String supprimerCat(Long id_utilisateur) {
		clientMetier.supprimerClient(id_utilisateur);
		return "redirect:listeClientPage";
	}
	// AFFICHAGE DE LA LISTE DES CATEGORIE*

	/*
	 * @RequestMapping(value="/listeClientMc") public String clientPmc(Model
	 * model,@RequestParam(name="motCle",defaultValue="")String mc, Pageable
	 * pageable,
	 * 
	 * @RequestParam(name="page", defaultValue="3")int p) { List<Client> c =
	 * clientMetier.listeClient(); model.addAttribute("cat", c);
	 * model.addAttribute("motCle",
	 * clientMetier.listeClientMc("%"+mc+"%",PageRequest.of(p,5))); return
	 * "ListeClient"; }
	 */
	@RequestMapping(value = "/listeClientPage")
	public String clientPagination(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u,
			// @RequestParam(name="pagination", defaultValue="0") int page,
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Client> c = clientRepository.clientParMotCle("%" + mc + "%", PageRequest.of(p, 7));
		// Page<Client> c = categorieMetier.findAll(PageRequest.of(page, p));
		int pcount = c.getTotalPages();
		int[] pages = new int[pcount];
		for (int i = 0; i < pcount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("client", c);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		
		Utilisateur utilisateurConncte=getLogedUserUc(hsr, model, id_utilisateur, u);
		model.addAttribute("utilisateur", utilisateurConncte);
		Long nc= nombreClient();
		model.addAttribute("nombre", nc);
		return "utilisateur/ListeClient";
	}

	@RequestMapping(value = "profilClient")
	public String profil(Model model) {
		String sms="bonjour java";
		model.addAttribute("sms", sms);
		return "profil/profilClient";
	}

	
	/*@RequestMapping(value = "/accueilClient")
	public String accueil(HttpServletRequest hsr, Model model, Long id_utilisateur, Utilisateur u) {
		String c = uc.getLogedUser(hsr, model, id_utilisateur, u);
		model.addAttribute("utilisateur", c);
		return "client/accueilClient";
	}*/
	 
	//@Value("${imageUtilisateur}")
	//private String photoUtila;
	@RequestMapping(value = "/accueilClient")
	//@Secured(value={"ROLE_CLIENT"})
	public String getLogedUser(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u){ 
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		model.addAttribute("username", username);
		Utilisateur utilisateur = ur.sessionEnCours(username);
		Long nc= nombreClient();
		model.addAttribute("nombre", nc);
		
		Long ac = am.nombreAvis(hsr);
		model.addAttribute("nombreAvisParClient", ac);
		
		model.addAttribute("utilisateur", utilisateur);
		Long ndrePV = pr.nrePV();
		model.addAttribute("nbrePresVal", ndrePV);
		
		Long ncsr = csr.nombreCategorie();
		model.addAttribute("nombreCategorie", ncsr);
		
		boolean b = utilisateur.isActived();
		if(b==true) {
			 return "client/accueilClient"; 
		}
	  return "site/login"; 
	  }

	/*@RequestMapping(value = "/profilClient2")
	public String getLogedUser(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u){ 
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		model.addAttribute("username", username);
		Utilisateur utilisateur = ur.sessionEnCours(username);
		Long cl = clientMetier.nombreClient(id_utilisateur);
		
		model.addAttribute("nombreClient", cl);
		model.addAttribute("utilisateur", utilisateur);
	  return "profil/profilClient"; 
	  }
	
	//edition d'un client
	@RequestMapping(value="/editionClient")
	public String editionClient(Client c,String password, @RequestParam(name = "picture") MultipartFile file) 
			throws  IOException {
		if (!(file.isEmpty())) {
			c.setPhoto_utilisateur(file.getOriginalFilename());
		}
		clientMetier.saveClient(c, password);
		if (!(file.isEmpty())) {
			c.setPhoto_utilisateur(file.getOriginalFilename());
			file.transferTo(new File(photoUtil + c.getId_utilisateur()));
		}
		return "redirect:profilClient2";
	}
	@RequestMapping(value="/editerClient")
	public String prepareEditionClient(Long id_utilisateur, Model model) {
		Utilisateur edition = um.GetUser(id_utilisateur);
		model.addAttribute("utilisateur", edition);
		return "profil/profilEdClient";
	}*/
	
	@RequestMapping(value="/detailClient")
	public String detailClient(HttpServletRequest hsr,Model model,Long id_utilisateur) {
		Client c = clientRepository.getOne(id_utilisateur);
		model.addAttribute("client", c);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "client/detailClient";
	}
	
	//methode a utiliser pour afficher le nom et la photo de l'utilisateur connecté
	public Utilisateur getLogedUserUc(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u){ 
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
	  return ur.sessionEnCours(username);
	  }
	@RequestMapping(value="/activerClient")
	public String  activation(Long id_utilisateur,Historique h,HttpServletRequest hsr ) {
		 
		  um.activation(id_utilisateur,h,hsr);
		  return "redirect:listeClientPage";
	}

	public Long  nombreClient() {
		return clientRepository.count();   
	}
	
}
