package com.projet.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.Entite.Abonne;
import com.projet.Entite.Message;
import com.projet.Entite.Utilisateur;
import com.projet.Metier.MessageMetier;

@Controller
public class MessageControlleur {

	@Autowired
	private MessageMetier mm;
	@Autowired
	private UtilisateurControlleur uc;
	
	@RequestMapping(value="/creationMessage", method = RequestMethod.POST)
	public String creationSmS(Message mes) {
		mm.creerMessage(mes);
		return "redirect:contactSite";
	}
	@RequestMapping(value="/contactSite")
	public String creation(Model model) {
		Message m = new Message();
		model.addAttribute("message", m);
		Abonne ab = new Abonne();
		model.addAttribute("abonne", ab);
		return "Site/contact";
	}
	@RequestMapping(value="listMessage")
	public String ListeSms(Model model,HttpServletRequest hsr) {
		List<Message> mes = mm.listeMessage();
		model.addAttribute("listeMessage", mes);
		Utilisateur u = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "Message/ListeMessage";
	}
}
