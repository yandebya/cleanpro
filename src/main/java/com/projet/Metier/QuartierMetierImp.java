package com.projet.Metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.QuartierRepository;
import com.projet.Dao.VilleRepository;
import com.projet.Entite.Quartier;
import com.projet.Entite.Ville;

@Service
public class QuartierMetierImp implements QuartierMetier{

	@Autowired
	private QuartierRepository qr;
	
	@Autowired
	private VilleRepository vr;
	@Override
	public Quartier creerQuartier(Quartier q,Long id_ville) {
		Ville v = vr.getOne(id_ville);
		q.setVille(v);
		return qr.save(q);
	}

	@Override
	public List<Quartier> listeQuartier() {
	
		return qr.findAll();
	}

}
