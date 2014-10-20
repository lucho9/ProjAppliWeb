package m2.project.repository;

import java.util.List;




import m2.project.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository  extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product> {
/*
	List<Product> findByName(String name);
	//List<Product> findById(Long Id);
	List<Product> findByCategory(String category);*/
}
