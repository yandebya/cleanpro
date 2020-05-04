package com.projet.Metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.VilleRepository;
import com.projet.Entite.Ville;

@Service
public class VilleMetierImp implements VilleMetier {

	@Autowired
	private VilleRepository vr;
	@Override
	public Ville creerVille(Ville v) {
		
		return vr.save(v);
	}

	@Override
	public List<Ville> listeVille() {
		
		return vr.findAll();
	}

}
