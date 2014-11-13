package m2.project.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	
	public Customer findOne(long id) {
		return customerRepository.findOne(id);
	}
	
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	public Page<Customer> findAllOrderByLastNameAsc(Pageable pageable) {
		return customerRepository.findAll(pageable);
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
	
	public void deleteAll() {
		customerRepository.deleteAll();
	}
	
	public List<Customer> findAll() {
		return customerRepository.findAll(new Sort(new Order(Direction.ASC, "lastName"), new Order(Direction.ASC, "firstName")));
	}
}
