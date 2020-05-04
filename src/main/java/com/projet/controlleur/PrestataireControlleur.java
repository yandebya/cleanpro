package com.projet.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Dao.NationaliteRepository;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Historique;
import com.projet.Entite.Nationalite;
import com.projet.Metier.CategorieServiceMetier;
import com.projet.Metier.PrestataireMetier;
import com.projet.Metier.QuartierMetier;
import com.projet.Metier.UtilisateurMetier;
import com.projet.Metier.VilleMetier;
import com.projet.Entite.Prestataire;
import com.projet.Entite.Quartier;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;


@Controller
public class PrestataireControlleur {

	@Autowired
	private NationaliteRepository nr;
	@Autowired
	private VilleMetier vm;
	@Autowired
	private QuartierMetier qm;
	@Autowired
	private UtilisateurMetier um;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private PrestataireRepository pr;
	@Autowired
	private PrestataireMetier pm;
	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private CategorieServiceMetier csm;
	  @Value("${imageUtilisateur}")
		private String photoUtila;
	  @Value("${imageUtilisateur}")
		private String photoPiece;
	@RequestMapping(value="/formPrestataire")
	public String choix() {
		return "prestataire/listePrestataire";
	}
	@RequestMapping(value = "/creationPrestataire", method = RequestMethod.POST)
	public String SaveAcomplet(Prestataire a, String password,@Valid BindingResult br,Long id_categorieServivce,
			@RequestParam(name = "picture") MultipartFile file, Model model
			)throws IOException {
	
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
				
			}
		//	pm.SavePrestataire(a, password,id_categorieServivce);
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
				file.transferTo(new File(photoUtila + a.getPhoto_utilisateur()));
				
				
			}
			model.addAttribute("sms", "yandebya rota");
			return "redirect:creerPrestataire";
		}
	@RequestMapping(value = "/postulerPrestataire", method = RequestMethod.POST)
	public String SaveAcompletPOSTULATION(Prestataire a,Long id_utilisateur, String password,@Valid BindingResult br,Long id_categorieServivce,
			@RequestParam(name = "picture") MultipartFile file,@RequestParam(name = "picturePiece") MultipartFile filePiece,
			@RequestParam(name = "pictureCasierJudiciaire") MultipartFile fileCasier,
			Model model,HttpServletRequest hsr,Long id_ville,  Long id_quartier,Long id_nationalite
			)throws IOException {
	
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
				a.setPiece_identite_pres(filePiece.getOriginalFilename());
				a.setCasier_judiciere_pres(fileCasier.getOriginalFilename());
			}
			pm.SavePrestataire(a,id_utilisateur, password,id_categorieServivce,id_ville,id_quartier,id_nationalite,hsr);
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
				file.transferTo(new File(photoUtila + a.getPhoto_utilisateur()));
				
				a.setPiece_identite_pres(filePiece.getOriginalFilename());
				filePiece.transferTo(new File(photoPiece + a.getPiece_identite_pres()));
				
				a.setCasier_judiciere_pres(fileCasier.getOriginalFilename());
				fileCasier.transferTo(new File(photoPiece+a.getCasier_judiciere_pres()));
			}
			model.addAttribute("sms", "yandebya rota");
			return "redirect:accueilPrestataire";
		}
	  //  @Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@RequestMapping(value = "/inscriptionPrestataire", method = RequestMethod.POST)
	public String SaveA(Prestataire a, String password,@Valid BindingResult br,
			@RequestParam(name = "picture") MultipartFile file, Model model
			)throws IOException {
	
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
			}
			pm.SavePrestataire(a, password);
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
				file.transferTo(new File(photoUtila + a.getPhoto_utilisateur()));
			}
			model.addAttribute("sms", "yandebya rota");
			return "redirect:creerPrestataire";
		}
	
	//inscription prestataire
	@RequestMapping(value = "/creerPrestataire")
	public String SavePres(Model model) {
		Prestataire c = new Prestataire();
		//Utilisateur u = new Utilisateur();
		//CategorieService ca = new CategorieService();
		//List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("prestataire", c);
		//model.addAttribute("utilisateur", u);
		//model.addAttribute("cs", ca);
		//model.addAttribute("categorieService",lcs);
		return "site/FormPrestataire";
	}
	@RequestMapping(value = "/postulerPrestataire")
	public String SavePresPostulation(Model model,Long id_utilisateur,HttpServletRequest hsr) {
		Utilisateur edition = pm.GetUserPerCat(id_utilisateur);
		model.addAttribute("prestataire", edition);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		CategorieService ca = new CategorieService();
		model.addAttribute("cs", ca);
		List<CategorieService> lcs = csm.liseCateServ();
		model.addAttribute("categorieService",lcs);
		
		Ville v = new Ville();
		List<Ville> vil = vm.listeVille();
		model.addAttribute("ville", v);
		model.addAttribute("listeVille",vil);
		
		Quartier q = new Quartier();
		List<Quartier> qart = qm.listeQuartier();
		model.addAttribute("quartier", q);
		model.addAttribute("listeQuartier",qart);
		
		Nationalite n = new Nationalite();
		List<Nationalite> nationalite = nr.findAll();
		model.addAttribute("nationalite", n);
		model.addAttribute("listeNationalite", nationalite);
		
		return "prestataire/FormPrestataire";
	}
	
	@RequestMapping(value = "/getPhoto_utilisateurPrestataire", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_utilisateurPrestataire(String photo_utilisateur) throws IOException {
		File f = new File(photoUtila + photo_utilisateur);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value = "/getPiece_identite_pres", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPiece_identite_pres(String piece_identite_pres) throws IOException {
		File f = new File(photoPiece + piece_identite_pres);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value = "/getCasier_judiciere_pres", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getCasier_judiciere_pres(String casier_judiciere_pres) throws IOException {
		File f = new File(photoPiece + casier_judiciere_pres);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	 //@Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN","ROLE_PRESTATAIRE"})
@RequestMapping(value = "/listePrestatairePage")
	public String adminPagination(HttpServletRequest hsr,Model model,Long id_utilisateur,
		// @RequestParam(name="pagination", defaultValue="0") int page,
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Prestataire> c = pr.prestataireParMotCleActive("%" + mc + "%", PageRequest.of(p, 9));
		// Page<Client> c = categorieMetier.findAll(PageRequest.of(page, p));
		int pcount = c.getTotalPages();
		int[] pages = new int[pcount];
		for (int i = 0; i < pcount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("prestataire", c);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return "prestataire/listePrestataire";
	}
	 @Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN","ROLE_PRESTATAIRE","ROLE_RECRUTEUR"})
@RequestMapping(value = "/listePrestatairePageNonValider")
	public String adminPaginationNV(HttpServletRequest hsr,Model model,Long id_utilisateur,
		// @RequestParam(name="pagination", defaultValue="0") int page,
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Prestataire> c = pr.prestataireParMotCle("%" + mc + "%", PageRequest.of(p, 6));
		// Page<Client> c = categorieMetier.findAll(PageRequest.of(page, p));
		int pcount = c.getTotalPages();
		int[] pages = new int[pcount];
		for (int i = 0; i < pcount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("prestataire", c);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Long nbrePNV = pr.nrePNV();
		model.addAttribute("nbrePresNonVal", nbrePNV);
		return "prestataire/listePrestataireNonValider";
	}
	 
	 
	 @Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN","ROLE_PRESTATAIRE","ROLE_RECRUTEUR"})
	 @RequestMapping(value = "/listePrestatairePageNonPost")
	 	public String adminPaginationNonPoster(HttpServletRequest hsr,Model model,Long id_utilisateur,
	 		// @RequestParam(name="pagination", defaultValue="0") int page,
	 			@RequestParam(name = "page", defaultValue = "0") int p,
	 			@RequestParam(name = "motCle", defaultValue = "") String mc) {
	 		Page<Prestataire> c = pr.prestataireParMotCleNonPost("%" + mc + "%", PageRequest.of(p, 6));
	 		// Page<Client> c = categorieMetier.findAll(PageRequest.of(page, p));
	 		int pcount = c.getTotalPages();
	 		int[] pages = new int[pcount];
	 		for (int i = 0; i < pcount; i++)
	 			pages[i] = i;
	 		model.addAttribute("pages", pages);
	 		model.addAttribute("prestataire", c);
	 		model.addAttribute("motCle", mc);
	 		model.addAttribute("pageCourant", p);
	 		
	 		Utilisateur uu = uc.getLogedUserUc(hsr);
	 		model.addAttribute("utilisateur", uu);
	 		
	 		Long nbrePNp = pr.nrePNp();
	 		model.addAttribute("nbrePresNonVal", nbrePNp);
	 		return "prestataire/listePrestataireNonPost";
	 	}
	 
	//edition d'un client
			@RequestMapping(value="/editionPrestataire")
			public String editionClient(Prestataire p,String password, @RequestParam(name = "picture") MultipartFile file,Long id_categorieServivce,Long id_ville, Long id_quartier) 
					throws  IOException {
				if (!(file.isEmpty())) {
					p.setPhoto_utilisateur(file.getOriginalFilename());
				}
				pm.editPrestataire(p, password,id_categorieServivce, id_ville,  id_quartier);
				if (!(file.isEmpty())) {
					p.setPhoto_utilisateur(file.getOriginalFilename());
					file.transferTo(new File(photoUtila + p.getPhoto_utilisateur()));
				}
				return "redirect:profilPrestataire";
			}
	  @Secured(value={"ROLE_PRESTATAIRE"})
		@RequestMapping(value="/editerPrestataire")
		public String prepareEditionClient(Long id_utilisateur, Model model ) {
			Utilisateur edition = pm.GetUserPerCat(id_utilisateur);
			model.addAttribute("utilisateur", edition);
			CategorieService ca = new CategorieService();
			model.addAttribute("cs", ca);
			List<CategorieService> lcs = csm.liseCateServ();
			model.addAttribute("categorieService",lcs);
			Ville v = new Ville();
			List<Ville> vil = vm.listeVille();
			model.addAttribute("ville", v);
			model.addAttribute("listeVille",vil);
			
			Quartier q = new Quartier();
			List<Quartier> qart = qm.listeQuartier();
			model.addAttribute("cquartier", q);
			model.addAttribute("quartier",qart);
			return "prestataire/profilEdPrestataire";
		}
		@Value("${imageUtilisateur}")
		private String photoUtil;
		@RequestMapping(value = "/profilPrestataire")
		public String getLogedUser(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u){ 
			HttpSession hs =hsr.getSession();
			SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
			String username=sc.getAuthentication().getName();
			model.addAttribute("username", username);
			Utilisateur utilisateur = ur.sessionEnCours(username);
			model.addAttribute("utilisateur", utilisateur);
			
		  return "prestataire/profilPrestataire"; 
		  }
		 @RequestMapping(value="/detailPrestataire")
			public String detailClient(HttpServletRequest hsr,Model model,Long id_utilisateur) {
				Utilisateur c = pm.GetUserPerCat(id_utilisateur);
				
						model.addAttribute("prestataire", c);
						Utilisateur u = uc.getLogedUserUc(hsr);
						model.addAttribute("utilisateur", u);
						Ville v = new Ville();
						Quartier q = new Quartier();
						//model.addAttribute("ville", v);
						//model.addAttribute("quartier",q);
				return "prestataire/detailPrestataire";
			}
		 
		 @RequestMapping(value="/activerPrestataire")
			public String  activation(Long id_utilisateur,Historique h,HttpServletRequest hsr) {
				 
				  um.activation(id_utilisateur,h,hsr);
				  return "redirect:listePrestatairePageNonValider";
			}
		 
		 @RequestMapping(value="/supprimerPrestataire")
			public String  delete(Long id_utilisateur ) {
				pm.supprimerPrestataire(id_utilisateur);
				  return "redirect:listePrestatairePage";
			}
		 @RequestMapping(value="/supprimerMultiplePrestataire")
		 public String deleteMultiple(Long id_utilisateur) {
			 pr.supprMultiple(id_utilisateur);
			 return "redirect:listePrestatairePageNonValider";
		 }
		 public Long nombrePrestataire() {
			 return pr.count();
		 }
		 @RequestMapping(value = "/accueilPrestataire")
			//@Secured(value={"ROLE_CLIENT"})
			public String acceuil(HttpServletRequest hsr,Model model,Long id_utilisateur,Utilisateur u){ 
			 Utilisateur utilisateur = uc.getLogedUserUc(hsr);
				model.addAttribute("utilisateur", utilisateur);
				String cp = utilisateur.getCode_postal_utilisateur();
				
				Ville v = new Ville();
				List<Ville> vil = vm.listeVille();
				model.addAttribute("ville", v);
				model.addAttribute("listeVille",vil);
				
				Quartier q = new Quartier();
				List<Quartier> qart = qm.listeQuartier();
				model.addAttribute("quartier", q);
				model.addAttribute("listeQuartier",qart);
				
				Nationalite n = new Nationalite();
				List<Nationalite> nationalite = nr.findAll();
				model.addAttribute("nationalite", n);
				model.addAttribute("listeNationalite", nationalite);
				
				CategorieService cs = new CategorieService();
				List<CategorieService> lcs = csm.liseCateServ();
				model.addAttribute("categorie", cs);
				model.addAttribute("listeCategorie", lcs);
				
				boolean b = utilisateur.isActived();
				if(b==true) {
					 return "prestataire/accueilPrestataire"; 
				}
				 return "prestataire/accueilPresNonPost"; 
			  }
		 @RequestMapping(value="/calendrier")
		 public String Calendrier(HttpServletRequest hsr,Model model) {
			 Utilisateur utilisateur = uc.getLogedUserUc(hsr);
				model.addAttribute("utilisateur", utilisateur);
			 return "calendrier/calendrier";
		 }
}

