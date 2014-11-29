package m2.project.repository;

import java.util.List;

import m2.project.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository  extends JpaRepository<Product, Long>{//, QueryDslPredicateExecutor<Product> {
/*
	List<Product> findByName(String name);
	//List<Product> findById(Long Id);
<<<<<<< HEAD
	List<Product> findByCategory(String category);
	
=======
	List<Product> findByCategory(String category);*/
	
	@Query("SELECT p FROM Product p WHERE (p.name) LIKE (%:searchTerm%)")
	public List<Product> find(@Param ("searchTerm")String searchTerm);
	
	@Query("SELECT p FROM Product p WHERE (p.name) LIKE (:searchTerm)")
	public List<Product> findOne(@Param ("searchTerm")String searchTerm);
	
	@Query("SELECT p FROM Product p WHERE (p.category.name) LIKE (%:searchTerm%)")
	public List<Product> findByCat(@Param ("searchTerm")String searchTerm);

	
	@Query("SELECT p FROM Product p WHERE (p.name) LIKE (%:searchTerm%) AND (p.prix) BETWEEN (:Min) AND (:Max) ")
	public List<Product> findByPrix(@Param ("searchTerm")String searchTerm,@Param ("Min")double Min,@Param ("Max")double Max);

	//public List<Product> find(String searchTerm);
    public Page<Product> findAll(Pageable page);	
	//public Page<Product> findAllOrderByLastNameAsc(Pageable page);	
	public List<Product> findAll();	

}
