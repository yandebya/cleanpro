package com.projet.Metier;

import javax.servlet.http.HttpServletRequest;

import com.projet.Entite.Admin;
import com.projet.Entite.Historique;

public interface AdminMetier {
public Admin creerAdmin(Admin a,Historique h,String password,HttpServletRequest hsr);
public Admin getAdmin(Long id_utilisateur);
}
