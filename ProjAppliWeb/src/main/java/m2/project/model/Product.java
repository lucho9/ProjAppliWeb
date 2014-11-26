package m2.project.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	@NotNull
	@Size(min=2, max=20)
	public String name;
	@NotNull
	@Min(1)
	private double prix;
	@NotNull
	@Min(1)
	private int stock;

	@OneToOne(mappedBy="product")
	private QuantiteCommande qc;
	
	public String ref="";

	@ManyToOne
	private Category category;

	public Product() {
	}

	public Product(String name, int prix) {
		this.name = name;
		this.prix = prix;
	}
	
	public Product(String name, double prix, int stock, Category category) {
		this.name = name;
		this.prix = prix;
		this.stock = stock;
		this.category = category;
	}
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public double getPrix() {
		return prix;
	}
	
	public double getPrixTTC() {
		return (prix + (prix * category.getTVA().getTva()));
	}
	
	public String getPrixTTCFormated() {
		DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getPrixTTC());
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public QuantiteCommande getQc() {
		return qc;
	}
	public void setQc(QuantiteCommande qc) {
		this.qc = qc;
	}
	public void setId(long id) {
		this.id = id;
	}
}
