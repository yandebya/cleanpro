package com.projet.Metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.CritereRepository;
import com.projet.Entite.Critere;

@Service
public class CritereMetierImp implements CritereMetier{

	@Autowired
	private CritereRepository cr;
	
	@Override
	public Critere creerCritere(Critere cri) {
		
		return cr.save(cri);
	}

	@Override
	public List<Critere> listeCritere() {
		return cr.findAll();
	}

}
