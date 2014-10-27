package m2.project.repository;

import m2.project.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Employee findEmployeeByLogin(String login);
}
