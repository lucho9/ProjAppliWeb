package m2.project.model;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Customer c;
	
	@OneToMany(mappedBy="facture", cascade=CascadeType.ALL)
	private List<QuantiteCommande> lq;
	
	private double totalHT = 0;
	private double totalTTC = 0;
	private double totalTTCDiscount = 0;
	private double discount = 0;
	
	private String ref = "";
	private double prixTotal;
	
	private String moyenPaiement;

	
	
	@NotNull
	private Date dateFacture;


	public Facture() {
	}
	
	public Facture(Customer c, List<QuantiteCommande> lq) {
		this.c = c;
		this.lq = lq;
	}
	
	public Facture(Panier n) {
		this.setC(n.getClient());
		this.setDateFacture(new Date());
		List<QuantiteCommande> lq = new ArrayList<QuantiteCommande>();
		for (QuantiteCommande p : n.getProductQuantities().values()) {
			p.setFacture(this);
			lq.add(p);
		}
		this.setLq(lq);
		this.setTotalHT(n.getTotalHT());
		this.setTotalTTC(n.getTotalTTC());
		this.setTotalTTCDiscount(n.getTotalTTCDiscount());
		this.setDiscount(n.getDiscount());
		this.setMoyenPaiement(n.getMoyenPaiement());
		DateFormat df = new SimpleDateFormat("yMd");
		this.setRef("#FACT" + df.format(this.getDateFacture()));
	}

	@JsonIgnore


	public String getMoyenPaiement() {
		return moyenPaiement;
	}

	public void setMoyenPaiement(String moyenPaiement) {
		this.moyenPaiement = moyenPaiement;
	}
	

	public List<QuantiteCommande> getLq() {
		return lq;
	}

	public void setLq(List<QuantiteCommande> lq) {
		this.lq = lq;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Customer getC() {
		return c;
	}
	public String getCustomerName() {
		return (c != null ? c.getCustomerName() : "");
	}
	public String getCustomerAddress() {
		return (c != null ? c.getAddress() : "");
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

	public double getTotalHT() {
		return totalHT;
	}

	public void setTotalHT(double totalHT) {
		this.totalHT = totalHT;
	}

	public double getTotalTTC() {
		return totalTTC;
	}

	public void setTotalTTC(double totalTTC) {
		this.totalTTC = totalTTC;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalTTCDiscount() {
		return totalTTCDiscount;
	}

	public void setTotalTTCDiscount(double totalTTCDiscount) {
		this.totalTTCDiscount = totalTTCDiscount;
	}

	public String getTotalHTFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(totalHT);
	}
	
	public String getTotalTTCFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(totalTTC);
	}
	
	public String getDiscountFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(discount) + " %";
	}
	
	public String getTotalTTCDiscountFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(totalTTCDiscount);
	}
	
}
