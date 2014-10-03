package m2.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private String name;
	private int prix;

	
	
	
	
	@ManyToOne
	private Category category ;
	
	@ManyToOne
	private Gamme gamme ;
	
	
	
	
	public Gamme getGamme() {
		return gamme;
	}

	public void setGamme(Gamme gamme) {
		this.gamme = gamme;
	}

	


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product() {

	}

	public Product(String name, int prix) {

		this.name = name;
		this.prix = prix;
	
	}

	public Long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	
	public void setId(Long id) {
		id = id;
	}
	
	
	
	
	
}
