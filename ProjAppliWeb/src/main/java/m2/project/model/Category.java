package m2.project.model;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Category {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private String name;
	
	
	public Category() {
	
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
