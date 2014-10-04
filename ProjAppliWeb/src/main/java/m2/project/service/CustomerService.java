package m2.project.service;

import java.util.List;

import m2.project.model.Customer;

public interface CustomerService {
	public Customer get(long id);
	public List<Customer> getAll();	
	public void save(Customer customer);
	public void delete(long id);
	public void delete(Customer customer);
}
