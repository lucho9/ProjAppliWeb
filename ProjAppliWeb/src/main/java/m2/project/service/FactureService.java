package m2.project.service;

import java.util.List;

import m2.project.model.Employee;
import m2.project.model.Facture;


public interface FactureService {

	public void save(Facture facture);
	public List<Facture> findByCustomerNames(String searchTerm1, String searchTerm2);
	public List<Facture> findAll();
	public void delete(long id);
	public Facture findOne(long id);
}
