package m2.project.repository;




import java.util.List;

import m2.project.model.Category;
import m2.project.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c WHERE (c.name) LIKE (:Term)")
	public List<Category> findOne(@Param ("Term")String Term);

	@Query("SELECT c FROM Category c WHERE (c.name) LIKE (%:searchTerm%)")
	public List<Category> find(String searchTerm);
	

	

	//public List<Product> find(String searchTerm);
    public Page<Category> findAll(Pageable page);	
	//public Page<Product> findAllOrderByLastNameAsc(Pageable page);	
	public List<Category> findAll();	
}
