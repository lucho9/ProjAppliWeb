package m2.project.repository;

import java.util.List;

import m2.project.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Employee findByLogin(String login);
	public Employee findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
	public Employee findByEmailIgnoreCase(String email);
	public Employee findByEmailAndLastNameAndFirstNameAllIgnoreCase(String email, String lastName, String firstName);
	public List<Employee> findDistinctByLastNameOrFirstName(String lastName, String firstName);
	public List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
	
	@Query("SELECT DISTINCT e FROM Employee e"
			+ " LEFT JOIN FETCH e.role"
			+ " WHERE (LOWER(e.lastName) LIKE %:searchTerm1%)"
			+ " OR (LOWER(e.lastName) LIKE %:searchTerm2%)"
			+ " OR (LOWER(e.firstName) LIKE %:searchTerm1%)"
			+ " OR (LOWER(e.firstName) LIKE %:searchTerm2%)"
	)
	public List<Employee> findByNames(@Param("searchTerm1") String searchTerm1, @Param("searchTerm2") String searchTerm2);
}
