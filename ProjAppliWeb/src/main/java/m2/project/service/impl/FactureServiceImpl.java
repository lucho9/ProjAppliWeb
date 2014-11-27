package m2.project.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import m2.project.model.Customer;
import m2.project.model.Employee;
import m2.project.model.Facture;
import m2.project.model.Panier;
import m2.project.model.QuantiteCommande;
import m2.project.repository.FactureRepository;
import m2.project.security.SecurityUserDetailsService;
import m2.project.service.EmployeeService;
import m2.project.service.FactureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceImpl implements FactureService{

	@Autowired
	FactureRepository frep;
	@Autowired
	EmployeeService employeeService; 
	
	public void save(Facture f) {
		Employee empLogged = employeeService.getLoggedEmployee();
    	if (empLogged != null)
    		f.setEmployee(empLogged);
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

    public void createFacture(Customer c, Map<Long, QuantiteCommande> m, String moyenPaiement, Date date, Employee employee) {
	    Panier pan = new Panier();
	    pan.setClient(c);
	    pan.setProductQuantities(m);
	    pan.setMoyenPaiement(moyenPaiement);
	    Facture f = new Facture(pan);
	    if (date != null)
	    	f.setDateFacture(date);
	    if (employee != null)
	    	f.setEmployee(employee);
	    frep.save(f);
    }
}
