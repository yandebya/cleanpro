package com.projet.Metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projet.Dao.PartenaireRepository;
import com.projet.Dao.RoleRepository;
import com.projet.Entite.Partenaire;
import com.projet.Entite.Role;

@Service
public class PartenaireMetierImp implements PartenaireMetier {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PartenaireRepository partenaireRepositrory;
	@Override
	public Partenaire creerPartenaire(Partenaire e, String password) {
		Role r = roleRepository.findByRole("PARTENAIRE");
		e.setRoles(new HashSet<Role> (Arrays.asList(r)));
		e.setPassword("{noop}"+password);
		e.setDate_inscription(new Date());
		return partenaireRepositrory.save(e);
	}

	@Override
	public List<Partenaire> listePartenaire() {
	
		return null;
	}

	@Override
	public Page<Partenaire> listePartenaireMc(String mc, Pageable pageable) {
		return partenaireRepositrory.partenaireParMotCle(mc, pageable);
	}

	@Override
	public Page<Partenaire> findPartenaire(Pageable pageable) {
		return partenaireRepositrory.findAll(pageable);
	}

}
