package com.projet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Utilisateur;
import com.projet.controlleur.UtilisateurControlleur;
@Configuration
public class CustomLoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler  {

	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private UtilisateurControlleur uc;
	
public void handle(HttpServletRequest request,HttpServletResponse response, Authentication authentication) 
			throws IOException {
		String targetUrl=determineTargetUrl(authentication);
	
		if(response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	public String determineTargetUrl(Authentication authentication) {
		String url="/login?error=true";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		
		
		//Utilisateur u = uc.getLogedUserUc(request);
		
		//boolean b = u.isActived();
		for(GrantedAuthority a: authorities) {
			roles.add(a.getAuthority());
			
		}
		
		if(roles.contains("ROLE_SUPERADMIN")) {
			url="/";
		}else if(roles.contains("ROLE_ADMIN")) {
			url="/";
		}
		else if(roles.contains("ROLE_RECRUTEUR")) {
			url="/accueilRecruteur";
		}
		else if(roles.contains("ROLE_EDITEUR")) {
			url="/accueilEditeur";
		}
		else if(roles.contains("ROLE_CLIENT")) {
			url="/accueilClient";
		}
		else if(roles.contains("ROLE_PARTENAIRE")) {
			url="/accueilClient";
		}
		else if(roles.contains("ROLE_PRESTATAIRE")) {
			url="/accueilPrestataire";
		}else {
			url="/forbidden";
		}
		
		return url;
		
	}
}
