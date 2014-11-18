package m2.project.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Facture {

	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	
	@ManyToOne
	private Customer c;
	
	@ManyToMany
	private List<Product> lp;
	
	@ManyToMany
	private List<QuantiteCommande> lq;
	
	private double prixTotal;


	
	public String ref="";


	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Facture(long id, Customer c, List<Product> lp,
			List<QuantiteCommande> lq, Long namee) {
		super();
		this.id = id;
		this.c = c;
		this.lp = lp;
		this.lq = lq;
		this.namee = namee;
	}

	public List<QuantiteCommande> getLq() {
		return lq;
	}

	public void setLq(List<QuantiteCommande> lq) {
		this.lq = lq;
	}
	private Long namee;
	

	public Facture(long id, Customer c, List<Product> lp, Long namee) {
		super();
		this.id = id;
		this.c = c;
		this.lp = lp;
		
		this.namee = namee;
	}

	public Long getNamee() {
		return namee;
	}

	public void setNamee(Long namee) {
		this.namee = namee;
	}

	public Facture() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Product> getLp() {
		return lp;
	}
	public void setLp(List<Product> lp) {
		this.lp = lp;
	}
	
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}

	
	
	
	
	
	
}
