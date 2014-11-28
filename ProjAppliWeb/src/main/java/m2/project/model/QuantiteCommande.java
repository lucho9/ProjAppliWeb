package m2.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class QuantiteCommande {

	@Id
	@GeneratedValue
	private long id;
	
	private int qte;
	
	@OneToOne
	private Product product;
	
	@ManyToOne
	private Facture facture;
	
	public QuantiteCommande() {
	}
	public QuantiteCommande(Product p, int qte) {
		this.qte = qte;
		this.product = p;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	@JsonIgnore
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	@JsonIgnore
	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
	public String getInfos() {
		return product.getName() + " " + qte;
	}
}
