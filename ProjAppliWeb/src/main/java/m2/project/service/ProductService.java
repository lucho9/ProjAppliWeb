package m2.project.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.model.Product;
public interface ProductService {
	 
    /**
     * Searches persons by using the given search term as a parameter.
     * @param searchTerm
     * @return  A list of persons whose last name begins with the given search term. If no persons is found, this method
     *          returns an empty list. This search is case insensitive.
     */
	public List<Product> findOne(String searchTerm);
	public List<Product> find(String searchTerm);
	public List<Product> findByCat(String searchTerm);
	//public List<Product> find(String searchTerm);
    public Page<Product> findAll(Pageable page);	
	//public Page<Product> findAllOrderByLastNameAsc(Pageable page);	
	public List<Product> findAll();	
	public void save(Product product);
	
	public void delete(long id);
	public void delete(Product product);
	public Product findOne(long id);

}