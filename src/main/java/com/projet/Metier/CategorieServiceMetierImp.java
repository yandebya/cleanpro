package com.projet.Metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.CategorieServiceRepository;
import com.projet.Entite.CategorieService;

@Service
public class CategorieServiceMetierImp implements CategorieServiceMetier {

	@Autowired
	private CategorieServiceRepository csr;

	@Override
	public CategorieService saveCat(CategorieService cs) {
		return csr.save(cs);
	}

	@Override
	public List<CategorieService> liseCateServ() {
		return csr.findAll();
	}

	@Override
	public CategorieService obtenirIdCatSer(Long id_categorieServivce) {
		
		return csr.getOne(id_categorieServivce);
	}

	@Override
	public void SupprimerCatSer(Long id_catServ) {
		csr.deleteById(id_catServ);
		
	}
}
