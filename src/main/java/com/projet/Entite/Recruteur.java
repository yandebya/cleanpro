package com.projet.Entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("recruteur")
public class Recruteur extends Utilisateur{

}
