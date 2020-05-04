package com.projet.Metier;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.CategorieServiceRepository;
import com.projet.Dao.ServiceRepository;
import com.projet.Dao.SousServiceRepository;
import com.projet.Entite.CategorieService;
import com.projet.Entite.Services;
import com.projet.Entite.SousServices;

@Service
public class SousServiceMetierImp implements SouServiceMetier {

	@Autowired
	private ServiceRepository sr;
	@Autowired
	private SousServiceRepository csr;
	
	@Override
	public SousServices creerSousServ(SousServices ss,Long id_service) {
		Services cs = sr.getOne(id_service);
		ss.setServices(cs);
		return csr.save(ss);
	}

	@Override
	public List<SousServices> listeSousSer() {
		
		return csr.findAll();
	}

	@Override
	public List<SousServices> listeSousService(Long id_service) {
		
		return csr.ListeSouSerParCat(id_service);
	}


}
