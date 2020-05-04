package com.projet.Metier;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.ServiceRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Services;

@Service
public class ServiceMetierImp implements ServiceMetier {

	@Autowired
	private ServiceRepository sr;
	@Autowired
	private CategorieServiceRepository csr;
	
	@Override
	public Services creerServ(Services s,Long id_categorieServivce) {
		CategorieService cs = csr.getOne(id_categorieServivce);
		s.setCategorieService(cs);
		return sr.save(s);
	}

	@Override
	public List<Services> listeSer() {
		
		return sr.findAll();
	}

	@Override
	public List<Services> listeService(Long id_categorie) {
		
		return sr.ListeSerParCat(id_categorie);
	}

}
