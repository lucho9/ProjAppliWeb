package m2.project.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import m2.project.model.serialization.EmployeeSerializer;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	private String ref="";
	
	@NotNull
	private Date dateFacture;

	public Facture(Customer c, double prixTotal) {
		super();
		this.c = c;
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

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
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

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}
	
	public String getDateFactureFormated() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(dateFacture);
	}

}
