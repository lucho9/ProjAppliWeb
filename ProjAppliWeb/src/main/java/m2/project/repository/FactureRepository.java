package m2.project.repository;

import java.util.List;

import m2.project.model.Facture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FactureRepository extends JpaRepository<Facture, Long>{
	
	@Query("SELECT DISTINCT f FROM Facture f"
			+ " LEFT JOIN f.c as cust"
			+ " WHERE (LOWER(cust.lastName) LIKE %:searchTerm1%)"
			+ " OR (LOWER(cust.lastName) LIKE %:searchTerm2%)"
			+ " OR (LOWER(cust.firstName) LIKE %:searchTerm1%)"
			+ " OR (LOWER(cust.firstName) LIKE %:searchTerm2%)"
	)
	public List<Facture> findByCustomerNames(@Param("searchTerm1") String searchTerm1, @Param("searchTerm2") String searchTerm2);
}
