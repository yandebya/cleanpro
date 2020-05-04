package com.projet.Entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypePrestation implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_typeP;
	private String libelleP;
	private long prixP;
	private Long prixT;
	private String critereA;
	private String critereB;
	private String critereC;
	private String critereD;
	private String critereE;
	public TypePrestation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TypePrestation(long id_typeP, String libelleP, long prixP,long prixT, String critereA, String critereB, String critereC,
			String critereD, String critereE) {
		super();
		this.id_typeP = id_typeP;
		this.libelleP = libelleP;
		this.prixP = prixP;
		this.prixT=prixT;
		this.critereA = critereA;
		this.critereB = critereB;
		this.critereC = critereC;
		this.critereD = critereD;
		this.critereE = critereE;
	
	}
	public long getId_typeP() {
		return id_typeP;
	}
	public void setId_typeP(long id_typeP) {
		this.id_typeP = id_typeP;
	}
	public String getLibelleP() {
		return libelleP;
	}
	public void setLibelleP(String libelleP) {
		this.libelleP = libelleP;
	}
	public long getPrixP() {
		return prixP;
	}
	public void setPrixP(long prixP) {
		this.prixP = prixP;
	}
	public String getCritereA() {
		return critereA;
	}
	public void setCritereA(String critereA) {
		this.critereA = critereA;
	}
	public String getCritereB() {
		return critereB;
	}
	public void setCritereB(String critereB) {
		this.critereB = critereB;
	}
	public String getCritereC() {
		return critereC;
	}
	public void setCritereC(String critereC) {
		this.critereC = critereC;
	}
	public String getCritereD() {
		return critereD;
	}
	public void setCritereD(String critereD) {
		this.critereD = critereD;
	}
	public String getCritereE() {
		return critereE;
	}
	public void setCritereE(String critereE) {
		this.critereE = critereE;
	}
	public Long getPrixT() {
		return prixT;
	}
	public void setPrixT(Long prixT) {
		this.prixT = prixT;
	}
	
	
}
