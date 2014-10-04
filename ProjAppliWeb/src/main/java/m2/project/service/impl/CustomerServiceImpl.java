package m2.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import m2.project.model.Customer;
import m2.project.repository.CustomerRepository;
import m2.project.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer get(long id) {
		return customerRepository.findOne(id);
	}
	
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}
	
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	public void delete(long id) {
		customerRepository.delete(id);
	}
	
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}
}
