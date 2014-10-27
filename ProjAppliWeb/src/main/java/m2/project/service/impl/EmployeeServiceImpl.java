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

import m2.project.model.Employee;
import m2.project.repository.EmployeeRepository;
import m2.project.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee findOne(long id) {
		return employeeRepository.findOne(id);
	}
	
	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
	public Page<Employee> findAllOrderByLastNameAsc(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	public void delete(long id) {
		employeeRepository.delete(id);
	}
	
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll(new Sort(new Order(Direction.ASC, "lastName"), new Order(Direction.ASC, "firstName")));
	}
	
	public Employee findEmployeeByLogin(String login) {
		return employeeRepository.findEmployeeByLogin(login);
	}
}
