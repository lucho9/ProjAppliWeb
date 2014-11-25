package m2.project.service.impl;

import java.util.List;

import m2.project.model.Category;

import m2.project.repository.CategoryRepository;
import m2.project.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findOne(String searchTerm) {
		// TODO Auto-generated method stub
		return categoryRepository.findOne(searchTerm);
	}
	
	
	public List<Category> find(String searchTerm) {
		return categoryRepository.find(searchTerm);
	}

	
	

	
	public Page<Category> findAll(Pageable page) {
		return categoryRepository.findAll(page);
	}

	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	
	public void save(Category category) {
		
		 categoryRepository.save(category);
		 
	}

	
	public void delete(long id) {
		 categoryRepository.delete(id);
		
	}

	
	public void delete(Category category) {
		 categoryRepository.delete(category);
		
	}

	
	public Category findOne(long id) {
		 return categoryRepository.findOne(id);
	}


	
	
	//créer une référence produit, voir comment la récupérer dans le .html
		public String getRef(Category p) {
			/*StringBuilder s = new StringBuilder();
			s.append("#");
	
			
			String refcat=p.getCategory().getName();
			refcat=refcat.substring(0, 2).toUpperCase();
			s.append(refcat);
			
			String refprod=p.getName().toUpperCase();
			refprod=refprod.substring(0, 2);
			s.append(refprod);
			
			String id=String.valueOf(p.getId());
			s.append(id);
			
			*/
			return "";
			
		}


		

}
