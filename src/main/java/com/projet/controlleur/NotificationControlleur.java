package com.projet.controlleur;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Dao.NotificationRepository;
import com.projet.Entite.Notification;
import com.projet.Entite.Utilisateur;

@Controller
public class NotificationControlleur {
	@Autowired
	public UtilisateurControlleur uc;
	@Autowired
	private NotificationRepository nr;
	
	@RequestMapping(value="/envoiNotification", method = RequestMethod.POST)
	public String notification( Notification n) {
		nr.save(n);
		return "redirect:receptionNotification";
	}
	
	@RequestMapping(value="/receptionNotification")
	public String notification2(Model model, HttpServletRequest hsr) {
		
		Notification n = new Notification();
		model.addAttribute("notification", n);

		Utilisateur utilisateur = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", utilisateur);
		
		return "notification/formNotification";
	}
}
