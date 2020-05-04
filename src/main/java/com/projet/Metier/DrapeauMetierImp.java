package com.projet.Metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.CouleurRepository;
import com.projet.Dao.DrapeauRepository;
import com.projet.Entite.EntiteCouleur;
import com.projet.Entite.EntiteDrapeau;


@Service
public class DrapeauMetierImp implements DrapeauMetier{

	
	@Autowired
	private CouleurRepository cr;
	
	@Autowired
	private DrapeauRepository dr;
	
	@Override
	public EntiteDrapeau drap(EntiteDrapeau ed, Long id_c) {	
			
		
		return creerDrapeau(ed,id_c);
		
		
		
		/*
		 * for (EntiteCouleur iterable_element : Arrays.asList(clr)) { ed.setCouleur(new
		 * HashSet<EntiteCouleur> (Arrays.asList(iterable_element))); dr.save(ed); }
		 */
		
	}
	
	public EntiteDrapeau creerDrapeau(EntiteDrapeau ed2,Long id_co) { 
		
		EntiteCouleur cc = cr.getOne(id_co);
		for(EntiteCouleur iterable_element : Arrays.asList(cc)) {
			
		ed2.setCouleur(new HashSet<EntiteCouleur> (Arrays.asList(iterable_element)));
	}
		return  dr.save(ed2);
	
}
	
	/*
	 * public EntiteDrapeau creerDrapeau(EntiteDrapeau ed2,EntiteCouleur
	 * cl,List<Long> id_co) {
	 * 
	 * List<EntiteCouleur> cc = cr.findAllById(id_co); for(List<EntiteCouleur>
	 * iterable_element : Arrays.asList(cc)) {
	 * 
	 * ed2.setCouleur(new HashSet<EntiteCouleur> (iterable_element)); } return
	 * dr.save(ed2);
	 * 
	 * }
	 */
	

}
