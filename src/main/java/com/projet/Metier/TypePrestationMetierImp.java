package com.projet.Metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.TypePrestationRepository;
import com.projet.Entite.TypePrestation;

@Service
public class TypePrestationMetierImp implements TypePrestationMetier{
	
	@Autowired
	public TypePrestationRepository tpr;

	@Override
	public TypePrestation enregistrerTP(TypePrestation tp, String ca) {
		
		tp.setCritereA(ca);
		Long pr = tp.getPrixP();
		Long pt = pr/3;
		tp.setPrixT(pt);
		return tpr.save(tp);
	}

	@Override
	public List<TypePrestation> listeTypeP() {
	
		return null;
	}
	
	

}
