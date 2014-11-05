package m2.project;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.repository.CategoryRepository;
import m2.project.repository.CustomerRepository;
import m2.project.repository.CustomerGroupRepository;
import m2.project.model.Product;
import m2.project.repository.ProductRepository;

import org.apache.tomcat.jni.File;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) throws Throwable {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		
		CustomerGroupRepository customerGroupRepository = context.getBean(CustomerGroupRepository.class);		
		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
		ProductRepository productRepository = context.getBean(ProductRepository.class);
		CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
		
		// customer groups
		CustomerGroup g = new CustomerGroup("Metro");
		CustomerGroup g2 = new CustomerGroup("Association des pêcheurs de Illzach");
		customerGroupRepository.save(g);
		customerGroupRepository.save(g2);
		
		List<CustomerGroup> customerGroupsList = new ArrayList<CustomerGroup>();
		
		// customers
		customerRepository.save(new Customer("Jack", "Bauer", null));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerRepository.save(new Customer("Chloe", "O'Brian", customerGroupsList));
		customerRepository.save(new Customer("Kim", "Bauer", customerGroupsList));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerGroupsList.add(g2);
		customerRepository.save(new Customer("David", "Palmer", customerGroupsList));
		customerRepository.save(new Customer("Michelle", "Dessler", customerGroupsList));
		customerRepository.save(new Customer("Johnny", "Cash", null));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerRepository.save(new Customer("Pam", "Anderson", customerGroupsList));
		customerRepository.save(new Customer("Powell", "Peralta", customerGroupsList));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerGroupsList.add(g2);
		customerRepository.save(new Customer("Titty", "Twister", customerGroupsList));
		customerRepository.save(new Customer("Remundo", "Do", customerGroupsList));
        
       
	       	
	       
	        //création des produits
	        Product p1= new Product("pomme golden",1,100);
	        Product p2= new Product("TV Samsung",250,50);
	        Product p3= new Product("TV Sony",200,20);
	        
	        
	      
	        Category c1=new Category("fruit");
	        Category c2=new Category("TV");

	        
	        //g.setId((long) 111);
	        
	        categoryRepository.save(c1);
	        categoryRepository.save(c2);
	        

	        //g.setStock(s);
	     
	        
	        //Set collec = new HashSet();
	        
	        p1.setCategory(c1);
	        p2.setCategory(c1);
	        p3.setCategory(c2);
	        
	 
	        
	        
	        productRepository.save(p1);
	        productRepository.save(p2);
	        productRepository.save(p3);
	     
    }
}
