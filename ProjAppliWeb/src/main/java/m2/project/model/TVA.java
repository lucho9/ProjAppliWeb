package m2.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class TVA {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	

	//La TVA représente la taxe sur la valeur ajoutée. 
	//Différents taux de TVA sont possibles : 19,6% dans la plupart des cas, 5.5% pour les denrées alimentaires et les livres 
	//et 2,1% pour les médicaments et les journaux.
	
	private double tva;
	
	@OneToMany(mappedBy="TVA")
	private List<Category> lc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	@JsonIgnore
	public List<Category> getLc() {
		return lc;
	}

	public void setLc(List<Category> lc) {
		this.lc = lc;
	}

	public TVA(double tva) {
		super();
		this.tva = tva;
	}

	public TVA(double tva, List<Category> lc) {
		super();
		this.tva = tva;
		this.lc = lc;
	}

	public TVA() {
		super();
	}
	
	
	
	
}
