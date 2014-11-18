package m2.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.model.Product;
import m2.project.repository.ProductRepository;
import m2.project.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import static m2.project.repository.ProductPredicates.nameIsLike;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository prep;

	public List<Product> findOne(String searchTerm) {
		
		return prep.findOne(searchTerm);
	}

	@Override
	public List<Product> find(String searchTerm) {
		return prep.find(searchTerm);
	}

	@Override
	public List<Product> findByCat(String searchTerm) {
		return prep.findByCat(searchTerm);
	}

	@Override
	public Page<Product> findAll(Pageable page) {
		return prep.findAll(page);
	}

	@Override
	public List<Product> findAll() {
		return prep.findAll();
	}

	
	public void save(Product product) {
		product.setRef(getRef(product));
		 prep.save(product);
		 
	}

	
	public void delete(long id) {
		 prep.delete(id);
		
	}

	
	public void delete(Product product) {
		 prep.delete(product);
		
	}

	
	public Product findOne(long id) {
		 return prep.findOne(id);
	}


	public List<Product> findByPrix(String searchTerm, int Min, int Max) {
	
		return prep.findByPrix(searchTerm, Min, Max);
	}
	
	//créer une référence produit, voir comment la récupérer dans le .html
		public String getRef(Product p) {
			StringBuilder s = new StringBuilder();
			s.append("#");
	
			
			String refcat=p.getCategory().getName();
			refcat=refcat.substring(0, 2).toUpperCase();
			s.append(refcat);
			
			String refprod=p.getName().toUpperCase();
			refprod=refprod.substring(0, 2);
			s.append(refprod);
			
			String id=String.valueOf(p.getId());
			s.append(id);
			
			
			return s.toString();
			
		}
		
	
}
