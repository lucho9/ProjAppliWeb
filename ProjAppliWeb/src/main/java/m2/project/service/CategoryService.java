package m2.project.service;

import java.util.List;

import m2.project.model.Category;
import m2.project.model.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    /**
     * Searches persons by using the given search term as a parameter.
     * @param searchTerm
     * @return  A list of persons whose last name begins with the given search term. If no persons is found, this method
     *          returns an empty list. This search is case insensitive.
     */
	public List<Category> findOne(String searchTerm);
	
	public List<Category> find(String searchTerm);

	//public List<Category> find(String searchTerm);
    public Page<Category> findAll(Pageable page);	
	//public Page<Category> findAllOrderByLastNameAsc(Pageable page);	
	public List<Category> findAll();	
	public void save(Category Category);
	
	public void delete(long id);
	public void delete(Category Category);
	public Category findOne(long id);
	
}

