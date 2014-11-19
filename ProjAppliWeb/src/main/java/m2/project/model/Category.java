package m2.project.model;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Category {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private String name;

	
	public Category() {
	
	}

	
	//La TVA représente la taxe sur la valeur ajoutée. 
	//Différents taux de TVA sont possibles : 19,6% dans la plupart des cas, 5.5% pour les denrées alimentaires et les livres 
	//et 2,1% pour les médicaments et les journaux.
	
	@ManyToOne
	private TVA TVA;
	


	public TVA getTVA() {
		return TVA;
	}



	public void setTVA(TVA tVA) {
		TVA = tVA;
	}



	public Category(String name, Collection<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}



	public Category(String laCategory) {
		this.name=laCategory;
	}
	
	
	
	@OneToMany(mappedBy="category")
	private Collection<Product> products ;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
	
	
}
