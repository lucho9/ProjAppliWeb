package m2.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.model.Employee;
import m2.project.model.Product;
import m2.project.model.Role;
import m2.project.repository.CategoryRepository;
import m2.project.repository.ProductRepository;
import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;
import m2.project.service.EmployeeService;
import m2.project.service.RoleService;

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
		
		CustomerGroupService customerGroupService = context.getBean(CustomerGroupService.class);		
		CustomerService customerService = context.getBean(CustomerService.class);
		ProductRepository productRepository = context.getBean(ProductRepository.class);

		RoleService roleService = context.getBean(RoleService.class);
		EmployeeService employeeService = context.getBean(EmployeeService.class);

		CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

		
		// customer groups
		CustomerGroup g = new CustomerGroup("Metro", 10);
		CustomerGroup g2 = new CustomerGroup("Association des pêcheurs de Illzach", 5);
		customerGroupService.save(g);
		customerGroupService.save(g2);
		
		List<CustomerGroup> customerGroupsList = new ArrayList<CustomerGroup>();
		
		// customers
		customerService.save(new Customer("Jack", "Butter", null));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerService.save(new Customer("Chloe", "O'Brian", customerGroupsList));
		customerService.save(new Customer("Kim", "Bauer", customerGroupsList));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerGroupsList.add(g2);
		customerService.save(new Customer("David", "Palmer", customerGroupsList));
		customerService.save(new Customer("Michelle", "Dessler", customerGroupsList));
		customerService.save(new Customer("Johnny", "Cash", null));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerService.save(new Customer("Pam", "Anderson", customerGroupsList));
		customerService.save(new Customer("Powell", "Peralta", customerGroupsList));
		customerGroupsList.clear();
		customerGroupsList.add(g);
		customerGroupsList.add(g2);
		customerService.save(new Customer("Titty", "Twister", customerGroupsList));
		customerService.save(new Customer("Remundo", "Do", customerGroupsList));
        

        
		
		// roles
		Role a = new Role("ROLE_ADMIN", "Administrateur");
		Role u = new Role("ROLE_USER", "Utilisateur");
		roleService.save(a);
		roleService.save(u);
		
		// employees
		GregorianCalendar cal = new GregorianCalendar(1947, 02, 16);
		employeeService.save(new Employee("Clint", "Westwood", "", "0033 3 00 00 00 01", "0033 6 00 00 00 01", "clint@gmail.com", new Date(cal.getTimeInMillis()), "admin", "admin", a));
		cal = new GregorianCalendar(1987, 01, 31);
		employeeService.save(new Employee("Brad", "Pett", "", "0033 3 00 00 00 02", "0033 6 00 00 00 02", "brad@gmail.com", new Date(cal.getTimeInMillis()), "user", "user", u));
		cal = new GregorianCalendar(1977, 05, 16);
		employeeService.save(new Employee("Robert", "Martin", "", "0033 3 00 00 00 03", "0033 6 00 00 00 03", "robert.martin@gmail.com", new Date(cal.getTimeInMillis()), "gomygamez@gmail.com", "", a));

       
	       	
	       
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
	        p2.setCategory(c2);
	        p3.setCategory(c2);
	        
	 
	        
	        
	        productRepository.save(p1);
	        productRepository.save(p2);
	        productRepository.save(p3);
	     

    }
}
