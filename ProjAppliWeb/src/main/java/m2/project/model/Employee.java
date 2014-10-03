package m2.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Employee {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String empNom;
	private String empPrenom;
	private String adresse;
	
	private int salaire;
	private int heureTravaillee;
	private int telephone;
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpNom() {
		return empNom;
	}
	public void setEmpNom(String empNom) {
		this.empNom = empNom;
	}
	public String getEmpPrenom() {
		return empPrenom;
	}
	public void setEmpPrenom(String empPrenom) {
		this.empPrenom = empPrenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public int getSalaire() {
		return salaire;
	}
	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	
	
	
	public int getHeureTravaillee() {
		return heureTravaillee;
	}
	public void setHeureTravaillee(int heureTravaillee) {
		this.heureTravaillee = heureTravaillee;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	
	
	
	
	
}
