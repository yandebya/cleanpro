package com.projet.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Dao.EditeurRepository;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Editeur;
import com.projet.Entite.Historique;
import com.projet.Entite.Prestataire;
import com.projet.Entite.Recruteur;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.EditeurMetier;
import com.projet.Metier.UtilisateurMetier;

@Controller
public class EditeurControlleur {
	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private  PrestataireRepository pr;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private EditeurMetier em;
	@Autowired
	private EditeurRepository editeurRepository;
	
	@Autowired
	private  ClientControlleur cc;
	@Autowired
	private PartenaireControlleur parr;
	@Autowired
	private PrestataireControlleur pc;
	@Autowired
	private UtilisateurMetier um;
	
	  @Value("${imageUtilisateur}")
	private String photoUtila;
		@RequestMapping(value = "/getPhoto_utilisateurEditeur", produces = MediaType.IMAGE_JPEG_VALUE)
		@ResponseBody
		public byte[] getPhoto_utilisateur(String photo_utilisateur) throws IOException {
			File f = new File(photoUtila + photo_utilisateur);
			return IOUtils.toByteArray(new FileInputStream(f));
		}

 	 @Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@RequestMapping(value = "/creationEditeur", method = RequestMethod.POST)
	public String SaveEditeur(Editeur a, String password, @RequestParam(name = "picture") MultipartFile file,Historique h,HttpServletRequest hsr)
			throws IOException {
		if (!(file.isEmpty())) {
			a.setPhoto_utilisateur(file.getOriginalFilename());
		}
		em.creerEditeur(a, password,h,hsr);
		if (!(file.isEmpty())) {
			a.setPhoto_utilisateur(file.getOriginalFilename());
			file.transferTo(new File(photoUtila + a.getId_utilisateur()));
		}

		return "redirect:listeEditeurPage";
	}
 	
	@RequestMapping(value = "/creerEditeur")
	public String SaveCl(Model model) {
		Recruteur c = new Recruteur();
		Utilisateur u = new Utilisateur();
		model.addAttribute("editeur", c);
		model.addAttribute("utilisateur", u);
		return "editeur/FormEditeur";
	}
	@Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
@RequestMapping(value = "/listeEditeurPage")
	public String recruteurPagination(HttpServletRequest hsr,Model model,Long id_utilisateur,
			// @RequestParam(name="pagination", defaultValue="0") int page,
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Editeur> c = em.listeEditeurMc("%" + mc + "%", PageRequest.of(p, 6));
		// Page<Recruteur> c = rm.findAll(PageRequest.of(page, p));
		int pcount = c.getTotalPages();
		int[] pages = new int[pcount];
		for (int i = 0; i < pcount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("editeur", c);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "editeur/ListeEditeur";
	}
	 @RequestMapping(value="/detailEditeur")
		public String detailClient(HttpServletRequest hsr,Model model,Long id_utilisateur) {
			Editeur c = editeurRepository.getOne(id_utilisateur);
					model.addAttribute("editeur", c);
					Utilisateur u = uc.getLogedUserUc(hsr);
					model.addAttribute("utilisateur", u);
			return "editeur/detailEditeur";
		}
	 
	 @RequestMapping(value = "/accueilEditeur")
		//@Secured(value={"ROLE_CLIENT"})
		public String getLogedUser(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u){ 
	
		 Utilisateur ut = uc.getLogedUserUc(hsr);
			model.addAttribute("utilisateur", ut);
		 
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
			
			
			
			
			boolean b = ut.isActived();
			if(b==true) {
				 return "editeur/accueilEditeur"; 
			}
		  return "site/login"; 
		  }
	 
	 @RequestMapping(value = "/profilEditeur")
		public String getLogedUser(HttpServletRequest hsr,Model model){ 
			Utilisateur u = uc.getLogedUserUc(hsr);
			model.addAttribute("utilisateur", u);
		  return "editeur/profilEditeur"; 
		  
		  }
	// @Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value="/editionEditeur")
		public String prepareEditionClient(Long id_utilisateur, Model model,Editeur a, String password) {
			
			em.editerEditeur(a, password);
			return "redirect:profilEditeur";
		}
	 
		@RequestMapping(value = "/edEditeur")
		public String edit(Model model,Long id_utilisateur,HttpServletRequest hsr) {
			Utilisateur c = ur.getOne(id_utilisateur);
			model.addAttribute("editeur", c);
			Utilisateur u = uc.getLogedUserUc(hsr);
			model.addAttribute("utilisateur", u);
			return "editeur/edEditeur";
		}
	 
}
