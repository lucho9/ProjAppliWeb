package m2.project.model;


import java.util.List;
import java.util.Random;

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
	
	private Random rand = new Random();
	
	@ManyToOne
	private Customer c;
	
	@ManyToMany
	private List<Product> lp;
	
	@ManyToMany
	private List<QuantiteCommande> lq;
	
	private double prixTotal;
	
	private String moyenPaiement;


	public Facture(long id, Customer c, List<Product> lp,
			List<QuantiteCommande> lq, double prixTotal, String moyenPaiement,
			int numFacture) {
		super();
		this.id = id;
		this.c = c;
		this.lp = lp;
		this.lq = lq;
		this.prixTotal = prixTotal;
		this.moyenPaiement = moyenPaiement;
		this.numFacture = numFacture;
	}

	public String getMoyenPaiement() {
		return moyenPaiement;
	}

	public void setMoyenPaiement(String moyenPaiement) {
		this.moyenPaiement = moyenPaiement;
	}

	public Facture(long id, Customer c, List<Product> lp,
			List<QuantiteCommande> lq, double prixTotal, int numFacture) {
		super();
		this.id = id;
		this.c = c;
		this.lp = lp;
		this.lq = lq;
		this.prixTotal = prixTotal;
		this.numFacture = numFacture;
	}

	private int numFacture;
	public int getNumFacture() {
		
		
		this.numFacture = rand.nextInt(10000 - 1000 + 1) + 1000;
		return numFacture;
	}

	public void setNumFacture(int numFacture) {
		this.numFacture = numFacture;
	}

	public String ref="";


	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Facture(long id, Customer c, List<Product> lp,
			List<QuantiteCommande> lq) {
		super();
		this.id = id;
		this.c = c;
		this.lp = lp;
		this.lq = lq;
	}

	public List<QuantiteCommande> getLq() {
		return lq;
	}

	public void setLq(List<QuantiteCommande> lq) {
		this.lq = lq;
	}
	
	public Facture(long id, Customer c, List<Product> lp) {
		super();
		this.id = id;
		this.c = c;
		this.lp = lp;
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

	public Facture(Customer c, double prixTotal) {
		super();
		this.c = c;
		this.prixTotal = prixTotal;
	}

	
	
	
	
	
	
}
