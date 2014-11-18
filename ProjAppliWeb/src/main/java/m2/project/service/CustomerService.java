package m2.project.service;

import java.util.List;

import m2.project.model.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
	public Customer findOne(long id);
	public Page<Customer> findAll(Pageable page);	
	public Page<Customer> findAllOrderByLastNameAsc(Pageable page);	
	public void save(Customer customer);
	public void delete(long id);
	public void delete(Customer customer);
	public void deleteAll();
	public List<Customer> findAll();
	public List<Customer> findByNames(String searchTerm1, String searchTerm2);
}
