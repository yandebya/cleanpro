package com.projet.Entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("editeur")
public class Editeur extends Utilisateur{

}
