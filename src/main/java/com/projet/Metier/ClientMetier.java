package com.projet.Metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projet.Entite.Client;
import com.projet.Entite.Utilisateur;


public interface ClientMetier {

	public Client saveClient(Client c,String password,HttpServletRequest hsr );
	public Client editClient(Client c,String password,HttpServletRequest hsr );
	public Utilisateur creerU(Utilisateur u);
	
	public List<Client> listeClient();
	public void supprimerClient(Long id_utilisateur);
	
	public Page<Client> listeClientMc(String mc, Pageable pageable);
	public Page<Client> findClient(Pageable pageable);
	
	public Utilisateur getId(Long idUtilisateur);
	public Long nombreClient(Long idclient);
}
