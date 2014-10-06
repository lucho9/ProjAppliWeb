package m2.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import m2.project.model.Customer;

public interface CustomerService {
	public Customer findOne(long id);
	public Page<Customer> findAll(Pageable page);	
	public Page<Customer> findAllOrderByLastNameAsc(Pageable page);	
	public void save(Customer customer);
	public void delete(long id);
	public void delete(Customer customer);
}
