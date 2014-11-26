package m2.project.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m2.project.repository.QuantiteCommandeRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class Panier {
	private Map<Long, QuantiteCommande> productQuantities = new HashMap<Long, QuantiteCommande>();
	
	
	private String moyenPaiement = "";
	
	
	public String getMoyenPaiement() {
		return moyenPaiement;
	}
	public void setMoyenPaiement(String moyenPaiement) {
		this.moyenPaiement = moyenPaiement;
	}
	private Map<Long, Product> products = new HashMap<Long, Product>();
	private Map<Long, Double> quantities = new HashMap<Long, Double>();
	private Customer client;
	
	public Customer getClient() {
		return client;
	}
	public long getClientId() {
		return (client != null ? client.getId() : 0);
	}
	public void setClient(Customer client) {
		this.client = client;
	}

	public void addProduct(Product product) {
		if (!productQuantities.containsKey(product.getId())) {
			productQuantities.put(product.getId(), new QuantiteCommande(product, 1.0));
		}
		else {
			QuantiteCommande q = productQuantities.get(product.getId());
			q.setQte(q.getQte() + 1);
			productQuantities.put(product.getId(), q);
		}
	}
	
	public void removeProduct(long id) {
		if (productQuantities.containsKey(id)) {
			productQuantities.remove(id);
		}		
	}

	public double getTotalHT() {
		double totalHT = 0;
		for (QuantiteCommande p : productQuantities.values()) {
			totalHT += (p.getQte() * p.getProduct().getPrix());
		}
		return totalHT;
	}
	
	public String getTotalHTFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getTotalHT());
	}
	
	public double getTotalTTC() {
		double totalTTC = 0;
		for (QuantiteCommande p : productQuantities.values()) {
			totalTTC += (p.getQte() * p.getProduct().getPrixTTC());
		}
		return totalTTC;
	}
	
	public String getTotalTTCFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getTotalTTC());
	}
	
	public double getDiscount() {
		if (client != null && client.getCustomerGroups() != null && !client.getCustomerGroups().isEmpty()) {
			return client.getCustomerGroups().get(0).getDiscount();
		}
		return 0;
	}
	
	public String getDiscountFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getDiscount()) + " %";
	}
	
	public double getTotalTTCDiscount() {
		if (client != null && client.getCustomerGroups() != null && !client.getCustomerGroups().isEmpty()) {
			return getTotalTTC() * (1 - (getDiscount() / 100));
		}
		return getTotalTTC();
	}
	
	public String getTotalTTCDiscountFormated() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getTotalTTCDiscount());
	}
	
	public Facture getFacture() {
		return new Facture(this);
	}
	public Map<Long, QuantiteCommande> getProductQuantities() {
		return productQuantities;
	}
	public void setProductQuantities(Map<Long, QuantiteCommande> productQuantities) {
		this.productQuantities = productQuantities;
	}
}
