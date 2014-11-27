package m2.project.service;

import java.util.List;

import m2.project.model.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
	public Employee findOne(long id);
	public Page<Employee> findAll(Pageable page);	
	public Page<Employee> findAllOrderByLastNameAsc(Pageable page);	
	public void save(Employee employee);
	public void delete(long id);
	public void delete(Employee employee);
	public void deleteAll();
	public List<Employee> findAll();
	public Employee findByLogin(String login);
	public Employee findByEmailIgnoreCase(String email);
	public Employee findByEmailAndLastNameAndFirstNameAllIgnoreCase(String email, String lastName, String firstName);
	public Employee findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
	public List<Employee> findByNames(String searchTerm1, String searchTerm2);
	public Employee getLoggedEmployee();
}
