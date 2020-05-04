package com.projet.controlleur;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Dao.PrestataireRepository;
import com.projet.Dao.ReservationRepository;
import com.projet.Dao.ServiceRepository;
import com.projet.Dao.SousServiceRepository;
import com.projet.Dao.TypePrestationRepository;
import com.projet.Dao.VilleRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Historique;
import com.projet.Entite.Prestataire;
import com.projet.Entite.Quartier;
import com.projet.Entite.Reservation;
import com.projet.Entite.Services;
import com.projet.Entite.SousServices;
import com.projet.Entite.TypePrestation;
import com.projet.Entite.Utilisateur;
import com.projet.Entite.Ville;
import com.projet.Metier.CategorieServiceMetier;
import com.projet.Metier.QuartierMetier;
import com.projet.Metier.ReservationMetier;
import com.projet.Metier.VilleMetier;

@Controller
public class ReservationControlleur {
	@Autowired
	private TypePrestationRepository tpr;
	@Autowired
	private SousServiceRepository ssr;
	@Autowired
	private CategorieServiceMetier cm;
	@Autowired
	private VilleMetier vm;
	@Autowired
	private QuartierMetier qm;
	@Autowired
	private VilleRepository vr;
	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private ServiceRepository sr;
	@Autowired
	private PrestataireRepository pr;
	@Autowired
	private ReservationMetier rm;
	@Autowired
	private ReservationRepository rr;
	
	@RequestMapping(value="/paie")
	public String paiement(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "Paiement/paiement";
	}
	
	@RequestMapping(value="/CreationReservation")
	public String reserver(Reservation r,Long id_ville,Long id_quartier,Long id_utilisateur,Long id_prestataire,Long id_categorieServivce,Long id_service,HttpServletRequest hsr) {
	
		rm.creerReservation(r, id_ville, id_quartier, id_utilisateur,id_categorieServivce,id_service,hsr);
		return "redirect:derReserverC";
	}
	@RequestMapping(value="/CreationReservationUnique")
	public String reserverUnique(Reservation r,Long id_ville,Long id_quartier,Long id_utilisateur,Long id_prestataire,Long id_categorieServivce,Long id_service,HttpServletRequest hsr) {
	
		rm.creerReservationUnique(r, id_ville, id_quartier, id_utilisateur,id_categorieServivce,id_service,hsr);
		return "redirect:derReserverC";
	}
	@RequestMapping(value="/reservationUnique")
	public String postulatioUniquen(Model model,HttpServletRequest hsr,Long id_categorie) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		
		Reservation r = new Reservation();
		model.addAttribute("reservation", r);
		
		List<CategorieService> cs = cm.liseCateServ();
		model.addAttribute("categorie", cs);
		
		Ville v = new Ville();
		List<Ville> vil = vm.listeVille();
		model.addAttribute("ville", v);
		model.addAttribute("listeVille",vil);
		
		Quartier q = new Quartier();
		List<Quartier> qart = qm.listeQuartier();
		model.addAttribute("quartier", q);
		model.addAttribute("listeQuartier",qart);
		
		Prestataire pre = new Prestataire();
		List<Prestataire> lpr = pr.findAll();
		model.addAttribute("prestataire",pre);
		model.addAttribute("listePrestataire",lpr);
		
		return "reservation/reservationUnique";
	}
	@RequestMapping(value="/reservation")
	public String postulation(Model model,HttpServletRequest hsr,Long id_categorie) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		
		Reservation r = new Reservation();
		model.addAttribute("reservation", r);
		
		List<CategorieService> cs = cm.liseCateServ();
		model.addAttribute("categorie", cs);
		
		Ville v = new Ville();
		List<Ville> vil = vm.listeVille();
		model.addAttribute("ville", v);
		model.addAttribute("listeVille",vil);
		
		Quartier q = new Quartier();
		List<Quartier> qart = qm.listeQuartier();
		model.addAttribute("quartier", q);
		model.addAttribute("listeQuartier",qart);
	
		return "reservation/reservation";
	}
	@RequestMapping(value="/reserverT")
	public String reservationtotal(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Reservation> rrr = rr.findAll();
		model.addAttribute("reserv", rrr);
		return "Paiement/paiement";
		
	}@RequestMapping(value="/reserverC")
	public String reservation(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Reservation> rr = rm.reservClient(hsr);
		model.addAttribute("reserv", rr);
		return "Paiement/paiement";
	}
	/*dernier reservation effectuer par le client*/
	@RequestMapping(value="/derReserverC")
	public String dernierReservation(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Reservation> rr = rm.derReservClient(hsr);
		model.addAttribute("reserv", rr);
		return "Paiement/paiement";
	}
	/*reservation pas encore consulter par le prestataire*/
	@RequestMapping(value="/derReserverP")
	public String dernierReservationPres(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Reservation> rr = rm.derReservPrestataire(hsr);
		model.addAttribute("reserv", rr);
		return "Paiement/derReserveP";
	}
	@RequestMapping(value="/reservationCat")
	public String postulater(Model model,HttpServletRequest hsr,Long id_categorie) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Services> services = sr.ListeSerParCat(id_categorie);
		model.addAttribute("service", services);
		Services se = new Services();
		model.addAttribute("ser", se);
		
		return "reservation/catReservation";
	}
	@RequestMapping(value="/reservationPres")
	public String choixPres(Model model,HttpServletRequest hsr,Long id_categorie) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Prestataire> prestataire = pr.ListePresParCat(id_categorie);
		model.addAttribute("prestataire", prestataire);
		Prestataire p = new Prestataire();
		model.addAttribute("pr", p);
		return "reservation/presReservation";
	}
	
	@RequestMapping(value="/reserver")
	public String reserver(Model model,HttpServletRequest hsr,Long id_categorie) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);

		return "reservation/reserver";
	}
	
	//sous service
	@RequestMapping(value="/reservationSousServices")
	public String sousService(Model model,HttpServletRequest hsr,Long id_service) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<SousServices> ss = ssr.ListeSouSerParCat(id_service);
		model.addAttribute("sousSer", ss);
		
		return "reservation/sousSerReservation";
	}
	
	@RequestMapping(value="/paieReservation")
	public String listeRe(Model m,HttpServletRequest hsr) {
		List<Reservation> liste = rm.listeReservation();
		m.addAttribute("reservation", liste);
		List<Ville> vv = vm.listeVille();
		//Ville v = vr.getOne(id_ville);
		Ville v = new Ville();
		m.addAttribute("ville2",v);
		m.addAttribute("ville", vv);
		Utilisateur u = uc.getLogedUserUc(hsr);
		m.addAttribute("utilisateur", u);
		return "Paiement/paiement";
	}
	@RequestMapping(value="verification")
	public String Verification(Model m,HttpServletRequest hsr) {
		List<Reservation> liste = rm.listeReservation();
		m.addAttribute("reservation", liste);
		List<Ville> vv = vm.listeVille();
		//Ville v = vr.getOne(id_ville);
		Ville v = new Ville();
		m.addAttribute("ville2",v);
		m.addAttribute("ville", vv);
		Utilisateur u = uc.getLogedUserUc(hsr);
		m.addAttribute("utilisateur", u);
		return "Paiement/paiement";
	}
	@RequestMapping(value="/resSousSere")
	public String test(Model m,HttpServletRequest hsr,Long id_service) {
		
		List<SousServices> ss = ssr.ListeSouSerParCat(id_service);
		m.addAttribute("sousSer", ss);
		
		return "reservation/sousSerReservation";
	}
	
	/*type de prestation*/
	@RequestMapping(value="/typeDeReservation")
	public String type(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<TypePrestation> ltp = tpr.findAll();
		model.addAttribute("typeP", ltp);
		return "typePrestation/typeReservation";
	}
	@RequestMapping(value="/listeReservation")
	public String typeListe(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Reservation> rrr = rm.reservClient(hsr);
		model.addAttribute("reserv", rrr);
		return "Paiement/paiementTout";
	}
	@RequestMapping(value="/listeReservationPrestataire")
	public String typeListePrestatire(Model model,HttpServletRequest hsr) {
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		List<Reservation> rrr = rm.reservPrestataire(hsr);
		model.addAttribute("reserv", rrr);
		return "Paiement/reservationEnCours";
	}
	
	 @RequestMapping(value="/activerReservation")
		public String  activation(Long id_reservation,HttpServletRequest hsr) {
			 
			  rm.activationReservation(id_reservation, hsr);
			  return "redirect:listeReservationPrestataire";
		}
	
}