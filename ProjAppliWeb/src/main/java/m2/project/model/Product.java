package m2.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Product {
	@Id
	@GeneratedValue
	
	private Long Id;
	private String name;
	private int prix;
	private String category;
	
	public Long getId(){
		return Id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String Category) {
		category = Category;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	
	
	
	
}
