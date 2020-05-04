package com.projet.Metier;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.projet.Entite.Historique;
import com.projet.Entite.Partenaire;
import com.projet.Entite.Utilisateur;


public interface UtilisateurMetier {
public Partenaire saveP(Partenaire p,String motDePasse);
public Partenaire savePsanProfil(Partenaire p);
public Utilisateur SessionU(String username);
public Utilisateur GetUser(Long id_utilisateur);
public void activation(Long id_utilisateur,Historique h,HttpServletRequest hsr);

}
