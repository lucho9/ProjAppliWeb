package m2.project.service;

import java.util.List;
import java.util.Map;

import m2.project.model.Customer;
import m2.project.model.Facture;
import m2.project.model.QuantiteCommande;


public interface FactureService {
	public void save(Facture facture);
	public List<Facture> findByCustomerNames(String searchTerm1, String searchTerm2);
	public List<Facture> findAll();
	public void delete(long id);
	public Facture findOne(long id);
    public void createFacture(Customer c, Map<Long, QuantiteCommande> m, String moyenPaiement);
}
