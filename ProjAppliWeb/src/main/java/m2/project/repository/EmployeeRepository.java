package m2.project.repository;

import java.util.List;

import m2.project.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Employee findByLogin(String login);
	public Employee findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
	public Employee findByEmailIgnoreCase(String email);
	public Employee findByEmailAndLastNameAndFirstNameAllIgnoreCase(String email, String lastName, String firstName);
	public List<Employee> findDistinctByLastNameOrFirstName(String lastName, String firstName);
	public List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
}
