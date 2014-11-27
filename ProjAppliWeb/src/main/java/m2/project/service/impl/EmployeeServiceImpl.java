package m2.project.service.impl;

import java.util.List;

import m2.project.model.Employee;
import m2.project.repository.EmployeeRepository;
import m2.project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public void deleteAll() {
		employeeRepository.deleteAll();
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll(new Sort(new Order(Direction.ASC, "lastName"), new Order(Direction.ASC, "firstName")));
	}
	
	public Employee findByLogin(String login) {
		return employeeRepository.findByLogin(login);
	}
	
	public Employee findByEmailIgnoreCase(String email) {
		return employeeRepository.findByEmailIgnoreCase(email);
	}
	
	public Employee findByEmailAndLastNameAndFirstNameAllIgnoreCase(String email, String lastName, String firstName) {
		return employeeRepository.findByEmailAndLastNameAndFirstNameAllIgnoreCase(email, lastName, firstName);
	}
	
	public List<Employee> findByNames(String searchTerm1, String searchTerm2) {
		return employeeRepository.findByNames(searchTerm1, searchTerm2);
	}
	
	public Employee findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {
		return employeeRepository.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
	}
	
	public Employee getLoggedEmployee() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String login = authentication.getName();
	    	if (login != null && !login.isEmpty()) {
	    		Employee e = findByLogin(login);
	    		if (e != null)
	    			return e;
	    	}
		}
		catch(Exception e) {
		}
    	return null;
	}
}
