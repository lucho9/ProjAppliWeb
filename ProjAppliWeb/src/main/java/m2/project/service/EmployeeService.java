package m2.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import m2.project.model.Employee;

public interface EmployeeService {
	public Employee findOne(long id);
	public Page<Employee> findAll(Pageable page);	
	public Page<Employee> findAllOrderByLastNameAsc(Pageable page);	
	public void save(Employee employee);
	public void delete(long id);
	public void delete(Employee employee);
	public List<Employee> findAll();
	public Employee findEmployeeByLogin(String login);
}
