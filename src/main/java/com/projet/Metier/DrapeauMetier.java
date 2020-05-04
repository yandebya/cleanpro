package com.projet.Metier;

import java.util.List;

import com.projet.Entite.EntiteCouleur;
import com.projet.Entite.EntiteDrapeau;

public interface DrapeauMetier {
	

	public EntiteDrapeau drap(EntiteDrapeau ed, Long id_c);
	public EntiteDrapeau creerDrapeau(EntiteDrapeau ed,Long id_co);
}
