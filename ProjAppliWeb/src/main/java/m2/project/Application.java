package m2.project;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.repository.CustomerRepository;
import m2.project.repository.CustomerGroupRepository;

import m2.project.model.Product;
import m2.project.repository.ProductRepository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

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
		CustomerGroup g = new CustomerGroup("Big Company");
		customerGroupRepository.save(g);
		
		// customers
		customerRepository.save(new Customer("Jack", "Bauer", g));
		customerRepository.save(new Customer("Chloe", "O'Brian", g));
		customerRepository.save(new Customer("Kim", "Bauer", g));
		customerRepository.save(new Customer("David", "Palmer", g));
		customerRepository.save(new Customer("Michelle", "Dessler", g));
        
        // products
		productRepository.save(new Product("pomme",1, ""));
		productRepository.save(new Product("poire",1, "fruit"));
		productRepository.save(new Product("banane",1, "fruit"));
		productRepository.save(new Product("kiwi",1, "fruit"));
		productRepository.save(new Product("courgette",1, "legume"));
    }
}
