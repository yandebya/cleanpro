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

import com.projet.Dao.PartenaireRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Editeur;
import com.projet.Entite.Partenaire;
import com.projet.Entite.Recruteur;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.CategorieServiceMetier;
import com.projet.Metier.PartenaireMetier;

@Controller
public class PartenaireControlleur {
	
	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private PartenaireMetier pm;

	@Autowired
	private PartenaireRepository partenaireRepository;
	
	@Value("${imageUtilisateur}")
	private String photoUtila;
 	@RequestMapping(value = "/getPhoto_utilisateurPartenaire", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_utilisateur(Long id_utilisateur) throws IOException {
		File f = new File(photoUtila + id_utilisateur);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
 	 @Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
 	@RequestMapping(value = "/creationPartenaire", method = RequestMethod.POST)
 	public String SaveEditeur(Partenaire a, String password, @RequestParam(name = "picture") MultipartFile file)
 			throws IOException {
 		if (!(file.isEmpty())) {
 			a.setPhoto_utilisateur(file.getOriginalFilename());
 		}
 		pm.creerPartenaire(a, password);
 		if (!(file.isEmpty())) {
 			a.setPhoto_utilisateur(file.getOriginalFilename());
 			file.transferTo(new File(photoUtila + a.getId_utilisateur()));
 		}

 		return "redirect:listePartenairePage";
 	}

	@RequestMapping(value = "/creerPartenaire")
	public String SaveCl(Model model) {
		Partenaire c = new Partenaire();
		Utilisateur u = new Utilisateur();
		model.addAttribute("partenaire", c);
		model.addAttribute("utilisateur", u);
		
		return "partenaire/FormPartenaire";
	}
	@Secured(value={"ROLE_ADMIN","ROLE_SUPERADMIN"})
@RequestMapping(value = "/listePartenairePage")
	public String recruteurPagination(HttpServletRequest hsr,Model model,Long id_utilisateur,
			// @RequestParam(name="pagination", defaultValue="0") int page,
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Partenaire> c = pm.listePartenaireMc("%" + mc + "%", PageRequest.of(p, 6));
		// Page<Recruteur> c = rm.findAll(PageRequest.of(page, p));
		int pcount = c.getTotalPages();
		int[] pages = new int[pcount];
		for (int i = 0; i < pcount; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		model.addAttribute("partenaire", c);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		Utilisateur u =uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "partenaire/ListePartenaire";
	}
	 @RequestMapping(value="/detailPartenaire")
		public String detailClient(HttpServletRequest hsr,Model model,Long id_utilisateur) {
			Partenaire c = partenaireRepository.getOne(id_utilisateur);
					model.addAttribute("partenaire", c);
					Utilisateur u = uc.getLogedUserUc(hsr);
					model.addAttribute("utilisateur", u);
			return "partenaire/detailPartenaire";
		}
	 
	 public Long nombrePartenaire() {
		 return partenaireRepository.count();
	 }
}
