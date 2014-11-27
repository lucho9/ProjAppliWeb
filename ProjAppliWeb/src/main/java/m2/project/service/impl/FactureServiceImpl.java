package m2.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import m2.project.model.Customer;
import m2.project.model.Employee;
import m2.project.model.Facture;
import m2.project.model.Panier;
import m2.project.model.QuantiteCommande;
import m2.project.repository.FactureRepository;
import m2.project.service.FactureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceImpl implements FactureService{

	@Autowired
	FactureRepository frep;
	
	public void save(Facture f) {
		frep.save(f);
	}
	
	public List<Facture> findByCustomerNames(String searchTerm1, String searchTerm2) {
		return frep.findByCustomerNames(searchTerm1, searchTerm2);
	}
	
	public void delete(long id) {
		frep.delete(id);
	}

	public List<Facture> findAll() {
		return frep.findAll(new Sort(new Order(Direction.DESC, "dateFacture"), new Order(Direction.ASC, "c.lastName"), new Order(Direction.ASC, "c.firstName")));
	}
	
	public Facture findOne(long id) {
		return frep.findOne(id);
	}
	
    public void createFacture(Customer c, Map<Long, QuantiteCommande> m, String moyenPaiement) {
	    Panier pan = new Panier();
	    pan.setClient(c);
	    pan.setProductQuantities(m);
	    pan.setMoyenPaiement(moyenPaiement);
	    save(new Facture(pan));
    }
}
