package m2.project.repository;

import java.util.List;

import m2.project.model.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Page<Customer> findAll(Pageable pageable);
	Customer findByLastName(String lastName);
	
	@Query("SELECT DISTINCT c FROM Customer c"
			+ " LEFT JOIN FETCH c.customerGroups"
			+ " WHERE (LOWER(c.lastName) LIKE %:searchTerm1%)"
			+ " OR (LOWER(c.lastName) LIKE %:searchTerm2%)"
			+ " OR (LOWER(c.firstName) LIKE %:searchTerm1%)"
			+ " OR (LOWER(c.firstName) LIKE %:searchTerm2%)"
	)
	public List<Customer> findByNames(@Param("searchTerm1") String searchTerm1, @Param("searchTerm2") String searchTerm2);
	
}
