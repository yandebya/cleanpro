package com.projet.Entite;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@DiscriminatorValue("partenaire")
public class Partenaire extends Utilisateur{
	private String nom_societe_part;
	private String logo_part;
	private String fiche_circuit_part;
	private String agrement_part;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_inscription_part;
	
	
	
}
