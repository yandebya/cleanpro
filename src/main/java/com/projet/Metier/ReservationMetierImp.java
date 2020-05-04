package com.projet.Metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.QuartierRepository;
import com.projet.Dao.ReservationRepository;
import com.projet.Dao.ServiceRepository;
import com.projet.Dao.TypePrestationRepository;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Dao.VilleRepository;
import com.projet.Entite.AvisClient;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Client;
import com.projet.Entite.Prestataire;
import com.projet.Entite.Quartier;
import com.projet.Entite.Reservation;
import com.projet.Entite.Role;
import com.projet.Entite.Services;
import com.projet.Entite.TypePrestation;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;

@Service
public class ReservationMetierImp implements ReservationMetier {

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private VilleRepository vr;
	@Autowired
	private QuartierRepository qr;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private PrestataireRepository pr;
	@Autowired
	private CategorieServiceRepository csr;
	@Autowired
	private ServiceRepository sr;
	@Autowired
	private TypePrestationRepository tp;

	@Override
	public Reservation creerReservation(Reservation r, Long id_ville,Long id_quartier,Long id_utilisateur,Long id_categorieService,Long id_service,HttpServletRequest hsr) {
		
		Services service = sr.getOne(id_categorieService);
		/*r.setRoles(new HashSet<Role> (Arrays.asList(r)));*/
		r.setService(new HashSet<Services> (Arrays.asList(service)));
		
		
		Ville v = vr.getOne(id_ville);
		Quartier q = qr.getOne(id_quartier);
		
		
		r.setVille(v);
		r.setQuartier(q);
		r.setDateReservation(new Date());
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username); 
		r.setType("Reservation multiples");
		r.setUtilisateur(utilisateur);
		
		Client client  = new Client();
		client.setId_utilisateur(utilisateur.getId_utilisateur());
		r.setClient(client);
		
		Prestataire der = pr.getOne(id_utilisateur);
		r.setPrestataire(der);
		CategorieService cs = csr.getOne(id_categorieService);
		r.setCategorieService(cs);

		
		return reservationRepository.save(r);
	}


	@Override
	public Reservation creerReservationUnique(Reservation r, Long id_ville, Long id_quartier, Long id_utilisateur,Long id_categorieServivce, Long id_service, HttpServletRequest hsr) {
		Services service = sr.getOne(id_categorieServivce);
		/*r.setRoles(new HashSet<Role> (Arrays.asList(r)));*/
		r.setService(new HashSet<Services> (Arrays.asList(service)));
		
		
		Ville v = vr.getOne(id_ville);
		Quartier q = qr.getOne(id_quartier);
		
		
		r.setVille(v);
		r.setQuartier(q);
		r.setDateReservation(new Date());
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username); 
		r.setType("Reservation Unique");
		r.setUtilisateur(utilisateur);
		
		Client client  = new Client();
		client.setId_utilisateur(utilisateur.getId_utilisateur());
		r.setClient(client);
		
		Prestataire der = pr.getOne(id_utilisateur);
		r.setPrestataire(der);
		CategorieService cs = csr.getOne(id_categorieServivce);
		r.setCategorieService(cs);

		
		return reservationRepository.save(r);

	}
	
	@Override
	public List<Reservation> listeReservation() {
		
		return reservationRepository.findAll();
	}


	
	@Override
	public List<Reservation> reservClient(HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		return reservationRepository.reserationClient(utilisateur.getId_utilisateur());
	}
	
	@Override
	public List<Reservation> reservPrestataire(HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		return reservationRepository.reserationPrestataire(utilisateur.getId_utilisateur());
	}

	@Override
	public List<Reservation> derReservClient(HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		return reservationRepository.dernierReserationClient(utilisateur.getId_utilisateur());
	}

	@Override
	public void activationReservation(Long id_reservation, HttpServletRequest hsr) {
		Reservation u = reservationRepository.getOne(id_reservation);
		boolean bo =u.isActived();

		if(bo==true){
			bo=false;
			}else {
				bo=true;
			}
		u.setActived(bo);
		reservationRepository.save(u);
		
	}

	@Override
	public List<Reservation> derReservPrestataire(HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
		return reservationRepository.dernierReserationprestataire(utilisateur.getId_utilisateur());
	}

}
