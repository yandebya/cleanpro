package com.projet.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Dao.MessageRepository;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.RecruteurRepository;
import com.projet.Entite.Admin;
import com.projet.Entite.Client;
import com.projet.Entite.Historique;
import com.projet.Entite.Recruteur;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;
import com.projet.Metier.RecruteurMetier;
import com.projet.Metier.UtilisateurMetier;

@Controller
public class RecruteurControlleur {

	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private UtilisateurMetier um;
	@Autowired
	private RecruteurMetier rm;
	@Autowired
	private RecruteurRepository rr;
	@Autowired
	private RecruteurRepository recruteurRepository;
	
	@Autowired
	private  PrestataireRepository pr;
	@Autowired
	private PartenaireControlleur parr;
	@Autowired
	private PrestataireControlleur pc;
	@Autowired
	private MessageRepository mr;
	  @Value("${imageUtilisateur}")
		private String photoUtila;
	@RequestMapping(value="/recruteur")
	public String recruteur() {
		return "recruteur/recruteur";
	}
	 @RequestMapping(value = "/creationRecruteur", method = RequestMethod.POST)
	public String SaveC(Recruteur cl, String password, @RequestParam(name = "picture") MultipartFile file,Historique h,HttpServletRequest hsr)
			throws IOException {
		if (!(file.isEmpty())) {
			cl.setPhoto_utilisateur(file.getOriginalFilename());
		}
		rm.creerRecruteur(cl, password,h,hsr);
		if (!(file.isEmpty())) {
			cl.setPhoto_utilisateur(file.getOriginalFilename());
			file.transferTo(new File(photoUtila + cl.getId_utilisateur()));
		}

		return "redirect:listeRecruteurPage";
	}
	
 	@RequestMapping(value = "/getPhoto_utilisateurRecruteur", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_utilisateur(Long id_utilisateur) throws IOException {
		File f = new File(photoUtila + id_utilisateur);
		return IOUtils.toByteArray(new FileInputStream(f));
	}

	@RequestMapping(value = "/creerRecruteur")
	public String SaveCl(Model model,HttpServletRequest hsr) {
		Recruteur c = new Recruteur();
		//Utilisateur u = new Utilisateur();
		model.addAttribute("recruteur", c);
		//model.addAttribute("utilisateur", u);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "recruteur/FormRecruteur";
	}
	@Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN","ROLE_RECRUTEUR"})
@RequestMapping(value = "/listeRecruteurPage")
	public String recruteurPagination(HttpServletRequest hsr,Model model,Long id_utilisateur,
			// @RequestParam(name="pagination", defaultValue="0") int page,
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Recruteur> c = rm.listeRecruteurMc("%" + mc + "%", PageRequest.of(p, 6));
		// Page<Recruteur> c = rm.findAll(PageRequest.of(page, p));
		int pcount = c.getTotalPages();
		int[] pages = new int[pcount];
		for (int i = 0; i < pcount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("recruteur", c);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		
		boolean b = u.isActived();
		if(b==true) {
			model.addAttribute("bloquer", "bloquer");
		}else {
			model.addAttribute("bloquer", "debloquer");
		}
		
		return "recruteur/ListeRecruteur";
	}
	
	 @RequestMapping(value="/detailRecruteur")
	public String detailClient(HttpServletRequest hsr,Model model,Long id_utilisateur) {
		Recruteur c = recruteurRepository.getOne(id_utilisateur);
				model.addAttribute("recruteur", c);
				Utilisateur u = uc.getLogedUserUc(hsr);
				model.addAttribute("utilisateur", u);
		return "recruteur/detailRecruteur";
	}
	 
	 @RequestMapping(value="/lisRecruteur")
		public String  listeRec(HttpServletRequest hsr,Long id_utilisateur , Model model) {
			 List<Recruteur> lr =recruteurRepository.findAll();
			 model.addAttribute("lr", lr);
			 Utilisateur u = uc.getLogedUserUc(hsr);
				model.addAttribute("utilisateur", u);
			 return "recruteur/ListeRecruteur";
		}
		/*supprimer*/
	@RequestMapping(value="supprimerRecruteur")
	public String  supprimerVille( Long id_utilisateur, Model model) {
	recruteurRepository.deleteById(id_utilisateur);
	return "redirect:listeRecruteurPage";
	}
	 @RequestMapping(value="/activerRecruteur")
		public String  activation(Long id_utilisateur,Historique h,HttpServletRequest hsr) {
			 
			  um.activation(id_utilisateur,h,hsr);
			  return "redirect:listeRecruteurPage";
		}
	 
	 @RequestMapping(value="editerRecruteur")
	  @ResponseBody
	private Recruteur editerRecruteur( Long id_utilisateur) {
	
		return rr.getOne(id_utilisateur);
	}
	 
	 
	 @RequestMapping(value = "/accueilRecruteur")
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
			
			Long prr = parr.nombrePartenaire();
			model.addAttribute("nombrePartenaire", prr);
			Long nprr = pc.nombrePrestataire();
			model.addAttribute("nombrePrestataire", nprr);
			
			
			Long sms = mr.count();
			model.addAttribute("nombreSmsVisiteur", sms);
			
			
			boolean b = ut.isActived();
			if(b==true) {
				 return "recruteur/accueilRecruteur"; 
			}
		  return "site/login2"; 
		  }
}
