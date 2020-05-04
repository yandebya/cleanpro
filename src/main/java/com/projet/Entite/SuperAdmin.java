package com.projet.Entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("superadmin")
public class SuperAdmin extends Utilisateur{

}
