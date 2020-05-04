package com.projet.Metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.Dao.RoleRepository;
import com.projet.Entite.Role;
import com.projet.Dao.ClientDao;
import com.projet.Dao.UtilisateurRepository;
import com.projet.Entite.Client;
import com.projet.Entite.Utilisateur;
import com.projet.controlleur.UtilisateurControlleur;

@Service
public class ClientMetierImp implements ClientMetier {
	@Autowired
	private ClientDao clientRepository;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private UtilisateurControlleur uc;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Client saveClient(Client c,String password,HttpServletRequest hsr ) {
		Utilisateur u =new Utilisateur();
		Role r = roleRepository.findByRole("CLIENT");
		c.setRoles(new HashSet<Role> (Arrays.asList(r)));
		c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
		c.setPhoto_utilisateur("user.jpg");
		c.setActived(true);
		/*HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
	
		//utilisateur.setNom_utilisateur(c.getNom_utilisateur());
		c.setEnregistrerPar(utilisateur.getNom_utilisateur());
		c.setEnregistrerParPrenom(utilisateur.getPrenom_utilisateur());*/
	
		c.setDate_inscription(new Date());
		return clientRepository.save(c);
	}
	@Override
	public Client editClient(Client c,String password,HttpServletRequest hsr ) {
		Utilisateur u =new Utilisateur();
		Role r = roleRepository.findByRole("CLIENT");
		c.setRoles(new HashSet<Role> (Arrays.asList(r)));
		c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
		
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT"); 
		String username=sc.getAuthentication().getName();
		Utilisateur utilisateur = ur.sessionEnCours(username);
	
		//utilisateur.setNom_utilisateur(c.getNom_utilisateur());
		c.setEnregistrerPar(utilisateur.getNom_utilisateur());
		c.setEnregistrerParPrenom(utilisateur.getPrenom_utilisateur());
		c.setActived(true);
		c.isActived();
		c.setDate_inscription(new Date());
		return clientRepository.save(c);
	}
	@Override
	public Utilisateur creerU(Utilisateur u) {
		
		return ur.save(u);
	}

	@Override
	public List<Client> listeClient() {
	
		return clientRepository.findAll();
	}

	@Override
	public void supprimerClient(Long id_utilisateur) {
		
		clientRepository.deleteById(id_utilisateur);
		
	}

	@Override
	public Page<Client> listeClientMc(String mc, Pageable pageable) {
		
		return clientRepository.clientParMotCle(mc,pageable);
	}

	@Override
	public Page<Client> findClient(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	@Override
	public Utilisateur getId(Long idUtilisateur) {
		
		return clientRepository.getOne(idUtilisateur);
	}

	@Override
	public Long nombreClient(Long idclient) {
		
		return clientRepository.count();
	}

}
