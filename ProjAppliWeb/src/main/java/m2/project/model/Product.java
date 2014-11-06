package m2.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Product {
	@Id
	@GeneratedValue
	
	private Long Id;
	private String name;
	private int prix;
	private String category;
	
	@ManyToMany(mappedBy="lp")
	List<Facture> lf;
	
	public Product() {
		super();
	}

	public Product(String name, int prix, String category) {
		super();
		this.name = name;
		this.prix = prix;
		this.category = category;
	}

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
	
	public List<Facture> getLf() {
		return lf;
	}

	public void setLf(List<Facture> lf) {
		this.lf = lf;
	}

	
	
	
}
