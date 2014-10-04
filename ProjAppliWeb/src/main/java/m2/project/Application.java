package m2.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.repository.CustomerRepository;
import m2.project.repository.CustomerGroupRepository;
import m2.project.model.Product;
import m2.project.repository.ProductRepository;

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
        
        // products
		productRepository.save(new Product("pomme",1, ""));
		productRepository.save(new Product("poire",1, "fruit"));
		productRepository.save(new Product("banane",1, "fruit"));
		productRepository.save(new Product("kiwi",1, "fruit"));
		productRepository.save(new Product("courgette",1, "legume"));
    }
}
