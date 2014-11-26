package m2.project;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.model.Employee;
import m2.project.model.Facture;
import m2.project.model.Panier;
import m2.project.model.Product;
import m2.project.model.QuantiteCommande;
import m2.project.model.Role;
import m2.project.model.TVA;
import m2.project.repository.CategoryRepository;
import m2.project.repository.ProductRepository;
import m2.project.repository.TVARepository;
import m2.project.service.CategoryService;
import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;
import m2.project.service.EmployeeService;
import m2.project.service.FactureService;
import m2.project.service.ProductService;
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
		//ProductRepository productRepository = context.getBean(ProductRepository.class);
		ProductService productService = context.getBean(ProductService.class);
		FactureService factureService= context.getBean(FactureService.class);
		RoleService roleService = context.getBean(RoleService.class);
		EmployeeService employeeService = context.getBean(EmployeeService.class);

		CategoryService categoryService = context.getBean(CategoryService.class);
		TVARepository tvaRepository = context.getBean(TVARepository.class);
		
		// customer groups
		CustomerGroup g1 = new CustomerGroup("Metro", 10);
		CustomerGroup g2 = new CustomerGroup("Association", 5);
		customerGroupService.save(g1);
		customerGroupService.save(g2);
		
		List<CustomerGroup> customerGroupsList1 = new ArrayList<CustomerGroup>();
		customerGroupsList1.add(g1);
		List<CustomerGroup> customerGroupsList2 = new ArrayList<CustomerGroup>();
		customerGroupsList2.add(g2);
		// customers
		customerService.save(new Customer("Jack", "Butter", "", null));
		customerService.save(new Customer("Chloe", "O'Brian", "", customerGroupsList1));
		customerService.save(new Customer("Kim", "Bauer", "", customerGroupsList2));
		customerService.save(new Customer("David", "Palmer", "", customerGroupsList1));
		customerService.save(new Customer("Michelle", "Dessler", "", customerGroupsList2));
		customerService.save(new Customer("Johnny", "Cash", "", null));
		Customer cust1 = new Customer("Pam", "Anderson", "Malibu", customerGroupsList1);
		customerService.save(cust1);
		customerService.save(new Customer("Powell", "Peralta", "", customerGroupsList2));
        
        // roles
		Role a = new Role("ROLE_ADMIN", "Administrateur");
		Role u = new Role("ROLE_USER", "Utilisateur");
		roleService.save(a);
		roleService.save(u);
		
		// employees
		GregorianCalendar cal = new GregorianCalendar(1947, 02, 16);
		employeeService.save(new Employee("Clint", "Westwood", "", "0033 3 00 00 00 01", "0033 6 00 00 00 01", "clint@gmail.com", new Date(cal.getTimeInMillis()), "admin", "admin", a));
		cal = new GregorianCalendar(1987, 1, 31);
		employeeService.save(new Employee("Brad", "Pett", "", "0033 3 00 00 00 02", "0033 6 00 00 00 02", "brad@gmail.com", new Date(cal.getTimeInMillis()), "user", "user", u));
		cal = new GregorianCalendar(1977, 5, 16);
		employeeService.save(new Employee("Robert", "Martin", "", "0033 3 00 00 00 03", "0033 6 00 00 00 03", "robert.martin@gmail.com", new Date(cal.getTimeInMillis()), "gomygamez@gmail.com", "robert", a));
		cal = new GregorianCalendar(1979, 8, 12);
		employeeService.save(new Employee("Julie", "Robert", "", "0033 3 00 00 00 04", "0033 6 00 00 00 04", "", new Date(cal.getTimeInMillis()), "", null, u));

        //création des produits
	    Product p1= new Product("pomme golden",1,100);
	    Product p2= new Product("TV Samsung",250,50);
	    Product p3= new Product("TV Sony",200,20);
	        
	    //Category c1=new Category("fruit", "#ffaa00");
	    //Category c2=new Category("TV", "#11dd66");

	    Category c1=new Category("fruit", "/ThemeTemplate/assets/img/fruit.jpg");
	    Category c2=new Category("TV", "/ThemeTemplate/assets/img/TV.png");
	    
	    TVA t1=new TVA(0.05);
	    TVA t2=new TVA(0.206);
	    
	    //Customer c=new Customer("David", "kjdbhhdsdsbg", customerGroupsList);
	  //  customerService.save(c);
		//factureService.save(new Facture( c, 562));
		
        
	        
	    tvaRepository.save(t1);
	    tvaRepository.save(t2);
	        
	    c1.setTVA(t1);
	    c2.setTVA(t2);
	        
	    //g.setId((long) 111);
	        
	    categoryService.save(c1);
	    categoryService.save(c2);
	        
	    //g.setStock(s);
	        
	    //Set collec = new HashSet();
	        
	    p1.setCategory(c1);
	    p2.setCategory(c2);
	    p3.setCategory(c2);
	        
	    productService.save(p1);
	    productService.save(p2);
	    productService.save(p3);
	    
	    Panier pan1 = new Panier();
	    pan1.setClient(cust1);
	    Map<Long, QuantiteCommande> m1 = new HashMap<Long, QuantiteCommande>();
	    m1.put(p1.getId(), new QuantiteCommande(p1, 3));
	    pan1.setProductQuantities(m1);
	    Facture f1 = new Facture(pan1);
	    factureService.save(f1);
    }
}
