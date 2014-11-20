package m2.project.model;

import java.util.HashMap;
import java.util.Map;

public class Panier {
	
	private double total = 0;
	private double totalTTC = 0;
	private double tempTVA = 0;
	
	private double qteGroupeDiscount = 0;
	private double prixGroupeDiscount = 0;
	
	private Map<Long, Product> products = new HashMap<Long, Product>();
	private Map<Long, Double> quantities = new HashMap<Long, Double>();
	
	public void addProduct(Product product) {
		if (!quantities.containsKey(product.getId())) {
			products.put(product.getId(), product);
			quantities.put(product.getId(), 1.0);
			tempTVA = tempTVA + product.getPrix() * (product.getCategory().getTVA().getTva());
			total = total + product.getPrix();
			
		}
		else {
			quantities.put(product.getId(), quantities.get(product.getId()) + 1);
			tempTVA = tempTVA + product.getPrix() * (product.getCategory().getTVA().getTva());
			total = total + product.getPrix();
		}
	}
	
	public void removeProduct(long id) {
		if (products.containsKey(id) && quantities.containsKey(id)) {
			total = total - (quantities.get(id) * products.get(id).getPrix());
			tempTVA=tempTVA-((quantities.get(id) * products.get(id).getPrix()) * (products.get(id).getCategory().getTVA().getTva()));
			
			quantities.remove(id);
			products.remove(id);
		}		
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalTTC() {
		return totalTTC;
	}

	public void setTotalTTC(double totalTTC) {
		this.totalTTC = totalTTC;
	}

	public double getTempTVA() {
		return tempTVA;
	}

	public void setTempTVA(double tempTVA) {
		this.tempTVA = tempTVA;
	}

	public double getQteGroupeDiscount() {
		return qteGroupeDiscount;
	}

	public void setQteGroupeDiscount(double qteGroupeDiscount) {
		this.qteGroupeDiscount = qteGroupeDiscount;
	}

	public double getPrixGroupeDiscount() {
		return prixGroupeDiscount;
	}

	public void setPrixGroupeDiscount(double prixGroupeDiscount) {
		this.prixGroupeDiscount = prixGroupeDiscount;
	}

	public Map<Long, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<Long, Product> products) {
		this.products = products;
	}

	public Map<Long, Double> getQuantities() {
		return quantities;
	}

	public void setQuantities(Map<Long, Double> quantities) {
		this.quantities = quantities;
	}
	
	
}
