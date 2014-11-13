package m2.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	
	@Column(unique=true)
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
	
	
	
	
	

	@ManyToMany(mappedBy="lp")
	List<Facture> lf;

	
	
	@ManyToOne
	private Category category ;

	//crr=éer une référence produit, voir comment la récupérer dans le .html
	public String getRef() {
		StringBuilder s = null;
		s.append("#");
		String refcat=this.category.getName();
		refcat.substring(0, 1);
		s.append(refcat);
		
		String refprod=this.getName();
		refprod.substring(0, 1);
		s.append(refprod);
		
		s.append(this.getId().toString());
		return s.toString();
		
	}
	
	   


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
	
	public List<Facture> getLf() {
		return lf;
	}

	public void setLf(List<Facture> lf) {
		this.lf = lf;
	}

	
	
	
}
