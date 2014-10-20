package m2.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import m2.project.model.Product;
import m2.project.repository.ProductRepository;
import m2.project.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static m2.project.repository.ProductPredicates.nameIsLike;

@Service
public class ProductServiceImpl implements ProductService{
	
	
	     
	    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	     
	    @Resource
	    private ProductRepository productRepository;
	 
	    @Transactional(readOnly = true)
	    @Override
	    public List<Product> search(String searchTerm) {
	        LOGGER.debug("Searching products with search term: " + searchTerm);
	 
	        //Passes the specification created by PersonPredicates class to the repository.
	        Iterable<Product> products = productRepository.findAll(nameIsLike(searchTerm));
	        return constructList(products);
	    }
	     
	    private List<Product> constructList(Iterable<Product> products) {
	        List<Product> list = new ArrayList<Product>();
	        for (Product product: products) {
	            list.add(product);
	        }
	        return list;
	    }
	
}
