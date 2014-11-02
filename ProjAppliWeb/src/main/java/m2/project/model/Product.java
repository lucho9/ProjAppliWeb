package m2.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	
	@NotNull
	@Size(min=2, max=20)
	public
	 String name;
	@NotNull
	@Min(1)
	private int prix;
	@NotNull
	@Min(1)
	private int stock;
	
	
	
	
	@ManyToOne
	private Category category ;
	

	
	   


	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
	
	
	public Product(String name, int prix,int stock) {
		
		this.name = name;
		this.prix = prix;
		this.stock = stock;
	
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
		this.id = id;
	}
	
	
	
	
	
}