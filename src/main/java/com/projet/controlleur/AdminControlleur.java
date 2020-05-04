package com.projet.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

import com.projet.Dao.AdminRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Admin;
import com.projet.Entite.Client;
import com.projet.Entite.Historique;
import com.projet.Entite.Recruteur;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.AdminMetier;
import com.projet.Metier.UtilisateurMetier;


@Controller
public class AdminControlleur {
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdminMetier adminMetier;
	@Autowired
	private UtilisateurMetier um;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private UtilisateurControlleur uc;
	@RequestMapping(value="/admin")
	public String admin(HttpServletRequest hsr,Model model){ 
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "admin/admin";
	}
	
	
	  @Value("${imageUtilisateur}")
	private String photoUtila;
	 // @Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@RequestMapping(value = "/creationAdmin", method = RequestMethod.POST)
	public String SaveA(@Valid Admin a,Historique h, String password,HttpServletRequest hsr, @RequestParam(name = "picture") MultipartFile file, BindingResult br)
			throws IOException {
		
		if(br.hasErrors()) {
			return "Admin/FormAdmin";
		}
		
		if (!(file.isEmpty())) {
			a.setPhoto_utilisateur(file.getOriginalFilename());
		}
		adminMetier.creerAdmin(a,h, password,hsr);
		if (!(file.isEmpty())) {
			a.setPhoto_utilisateur(file.getOriginalFilename());
			file.transferTo(new File(photoUtila + a.getPhoto_utilisateur()));
		}

		return "redirect:listeAdminPage";
	}

	@RequestMapping(value = "/getPhoto_utilisateurAdmin", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_utilisateur(String photo_utilisateur) throws IOException {
		File f = new File(photoUtila + photo_utilisateur);
		return IOUtils.toByteArray(new FileInputStream(f));
	}

	@RequestMapping(value = "/creerAdmin")
	public String SaveCl(HttpServletRequest hsr,Model model) {
		Admin c = new Admin();
		Utilisateur u = new Utilisateur();
		model.addAttribute("admin", c);
		model.addAttribute("utilisateur", u);
		Utilisateur ut = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", ut);
		return "Admin/FormAdmin";
	}
	@Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
@RequestMapping(value = "/listeAdminPage")
	public String adminPagination(HttpServletRequest hsr,Model model,Long id_utilisateur,
			// @RequestParam(name="pagination", defaultValue="0") int page,
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Admin> c = adminRepository.adminParMotCle("%" + mc + "%", PageRequest.of(p, 6));
		// Page<Client> c = categorieMetier.findAll(PageRequest.of(page, p));
		int pcount = c.getTotalPages();
		int[] pages = new int[pcount];
		for (int i = 0; i < pcount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("admin", c);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		Utilisateur ut = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", ut);
		return "admin/ListeAdmin";
	}
	//edition d'un client
		@RequestMapping(value="/editionAdmin")
		public String editionClient(Admin a,Historique h,String password,HttpServletRequest hsr, @RequestParam(name = "picture") MultipartFile file) 
				throws  IOException {
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
			}
			adminMetier.creerAdmin(a, h,password,hsr);
			if (!(file.isEmpty())) {
				a.setPhoto_utilisateur(file.getOriginalFilename());
				file.transferTo(new File(photoUtila + a.getPhoto_utilisateur()));
			}
			return "redirect:profilAdmin";
		}
		/*@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value="/editerAdmin")
		public String prepareEditionClient(Long id_utilisateur, Model model) {
			Utilisateur edition = um.GetUser(id_utilisateur);
			model.addAttribute("utilisateur", edition);
			return "admin/profilEdAdmin";
		}*/
	
		@RequestMapping(value = "/profilAdmin")
		public String getLogedUser(HttpServletRequest hsr,Model model){ 
			Utilisateur u = uc.getLogedUserUc(hsr);
			model.addAttribute("utilisateur", u);
		  return "admin/profilAdmin"; 
		  
		  }
		
		/*supprimer*/
		@RequestMapping(value="supprimerAdmin")
		public String  supprimerVille( Long id_utilisateur, Model model) {
			adminRepository.deleteById(id_utilisateur);
		return "redirect:listeAdminPage";
		}
		 @RequestMapping(value="/activerAdmin")
			public String  activation(Long id_utilisateur,Historique h,HttpServletRequest hsr) {
				 
				  um.activation(id_utilisateur,h,hsr);
				  return "redirect:listeAdminPage";
			}
		 
		 @RequestMapping(value="editerAdmin")
		  @ResponseBody
		private Admin editerRecruteur( Long id_utilisateur) {
		
			return adminRepository.getOne(id_utilisateur);
		}
		 
		 
		
}
