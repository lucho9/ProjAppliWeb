package m2.project.repository;

import java.util.List;

import m2.project.model.Customer;
import m2.project.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByName(String name);
	
}
