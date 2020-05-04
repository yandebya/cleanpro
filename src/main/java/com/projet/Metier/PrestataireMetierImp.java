 package com.projet.Metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.NationaliteRepository;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.QuartierRepository;
import com.projet.Dao.RoleRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Dao.VilleRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Nationalite;
import com.projet.Entite.Prestataire;
import com.projet.Entite.Quartier;
import com.projet.Entite.Role;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;

@Service
public class PrestataireMetierImp implements PrestataireMetier {
	@Autowired
private RoleRepository roleRepository;
	@Autowired
	private PrestataireRepository prestataireRepository;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private VilleRepository vr;
	@Autowired
	private QuartierRepository qr;
	@Autowired
	private NationaliteRepository nr;
	@Autowired
	private CategorieServiceMetier csm;
	
	//inscription prestataire
	@Override
	public Prestataire SavePrestataire(Prestataire pres,String password) {
		Role r = roleRepository.findByRole("PRESTATAIRE");
		pres.setRoles(new HashSet<Role> (Arrays.asList(r)));
	
		pres.setPassword(new BCryptPasswordEncoder().encode(pres.getPassword()));
		CategorieService ccss = new CategorieService((long) 1,"");
		
		pres.setCategorieService(ccss);
		pres.setDate_inscription(new Date());
		pres.setPhoto_utilisateur("téléchargement.jpg");
		pres.setPiece_identite_pres("téléchargement.jpg");
		pres.setCasier_judiciere_pres("téléchargement.jpg");
		Ville v = new Ville((long) 1,"choix ville");
		pres.setVille(v);
		
		Quartier q = new Quartier((long) 1,"choix quartier");
		pres.setQuartier(q);
		
		return prestataireRepository.save(pres);
	}
	@Override
	public Prestataire SavePrestataire(Prestataire pres,Long id_utilisateur, String password, Long id_categorieServivce,Long id_ville, Long id_quartier,Long id_nationalite,HttpServletRequest hsr) {
		Role r = roleRepository.findByRole("PRESTATAIRE");
		pres.setRoles(new HashSet<Role> (Arrays.asList(r)));
		CategorieService cs = csm.obtenirIdCatSer(id_categorieServivce);
		Ville v = vr.getOne(id_ville);
		Quartier q = qr.getOne(id_quartier);
		Nationalite n = nr.getOne( id_nationalite);
		
		pres.setVille(v);
		pres.setQuartier(q);
		pres.setNationalite(n);
		pres.setPassword(pres.getPassword());
		pres.setDate_inscription(new Date());
		pres.setCategorieService(cs);
		
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		
		pres.setId_utilisateur(utilisateur.getId_utilisateur());
		pres.setNom_utilisateur(utilisateur.getNom_utilisateur());
		pres.setPrenom_utilisateur(utilisateur.getPrenom_utilisateur());
		pres.setUsername(utilisateur.getUsername());
		pres.setPassword(utilisateur.getPassword());
		return prestataireRepository.save(pres);
	}

	@Override
	public Prestataire editPrestataire(Prestataire pres,String password,Long id_categorieServivce,Long id_ville, Long id_quartier) {
		Role r = roleRepository.findByRole("PRESTATAIRE");
		pres.setRoles(new HashSet<Role> (Arrays.asList(r)));
		CategorieService cs = csm.obtenirIdCatSer(id_categorieServivce);
		pres.setPassword(new BCryptPasswordEncoder().encode(pres.getPassword()));
		pres.setDate_inscription(new Date());
		pres.setActived(true);
		pres.setCategorieService(cs);
		Ville v = vr.getOne(id_ville);
		Quartier q = qr.getOne(id_quartier);
		
		pres.setVille(v);
		pres.setQuartier(q);
		return prestataireRepository.save(pres);
	}
	@Override
	public Long nobrePNV() {
	
		return prestataireRepository.nrePNV();
	}

	@Override
	public void supprimerPrestataire(Long id_utilisateur) {
		prestataireRepository.deleteById(id_utilisateur);
	}
	@Override
	public Utilisateur GetUserPerCat(Long id_utilisateur) {
		
		return prestataireRepository.getUserCategorie(id_utilisateur);
	}
	
}
