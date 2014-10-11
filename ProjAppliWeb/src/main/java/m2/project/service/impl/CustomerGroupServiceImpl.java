package m2.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import m2.project.model.CustomerGroup;
import m2.project.repository.CustomerGroupRepository;
import m2.project.repository.CustomerRepository;
import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;

@Service
@Transactional
public class CustomerGroupServiceImpl implements CustomerGroupService {

	@Autowired
	CustomerGroupRepository customerGroupRepository;
	
	public CustomerGroup findOne(long id) {
		return customerGroupRepository.findOne(id);
	}
	
	public List<CustomerGroup> findAll() {
		return customerGroupRepository.findAll();
	}
	
	public void save(CustomerGroup customerGroup) {
		customerGroupRepository.save(customerGroup);
	}

	public void delete(long id) {
		customerGroupRepository.delete(id);
	}
	
	public void delete(CustomerGroup customerGroup) {
		customerGroupRepository.delete(customerGroup);
	}
}
