package m2.project;

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
		Customer cust1 = new Customer("Jack", "Butter", "", null);
		Customer cust2 = new Customer("Chloe", "O'Brian", "", customerGroupsList1);
		Customer cust3 = new Customer("Kim", "Bauer", "", customerGroupsList2);
		Customer cust4 = new Customer("David", "Palmer", "", customerGroupsList1);
		Customer cust5 = new Customer("Michelle", "Dessler", "", customerGroupsList2);
		Customer cust6 = new Customer("Johnny", "Cash", "", null);
		Customer cust7 = new Customer("Pam", "Anderson", "Malibu", customerGroupsList1);
		customerService.save(cust1);
		customerService.save(cust2);
		customerService.save(cust3);
		customerService.save(cust4);
		customerService.save(cust5);
		customerService.save(cust6);
		customerService.save(cust7);
        
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

		// TVA
	    TVA t1 = new TVA(0.05);
	    TVA t2 = new TVA(0.206);
	    tvaRepository.save(t1);
	    tvaRepository.save(t2);
	    
		// catégories de produits
	    Category c1 = new Category("Fruit", "/ThemeTemplate/assets/img/fruit.jpg", t1);
	    Category c2 = new Category("TV", "/ThemeTemplate/assets/img/TV.png", t2);
	    Category c3 = new Category("Boisson", "/ThemeTemplate/assets/img/boisson.jpg", t1);
	    categoryService.save(c1);
	    categoryService.save(c2);
	    categoryService.save(c3);
	    
        //création des produits
	    Product p11 = new Product("Citron", 1.05, 1000, c1);
	    Product p12 = new Product("Papaye", 2.3, 1000, c1);
	    Product p13 = new Product("Kiwi", 1.5, 700, c1);
	    Product p21 = new Product("Samsung", 1000, 50, c2);
	    Product p22 = new Product("Sony", 900, 20, c2);
	    Product p23 = new Product("LG", 1400, 20, c2);
	    Product p31 = new Product("Coke", 1.5, 1000, c3);
	    Product p32 = new Product("RedBull", 2.5, 800, c3);
	    Product p33 = new Product("Tequila", 15, 200, c3);
	    productService.save(p11);
	    productService.save(p12);
	    productService.save(p13);
	    productService.save(p21);
	    productService.save(p22);
	    productService.save(p23);
	    productService.save(p31);
	    productService.save(p32);
	    productService.save(p33);
	    
	    // factures
	    HashMap<Long, QuantiteCommande> m1 = new HashMap<Long, QuantiteCommande>();
	    m1.put(p12.getId(), new QuantiteCommande(p12, 3));
	    m1.put(p22.getId(), new QuantiteCommande(p22, 1));
	    m1.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(cust7, m1, "Chèque");
	    HashMap<Long, QuantiteCommande> m2 = new HashMap<Long, QuantiteCommande>();
	    m2.put(p11.getId(), new QuantiteCommande(p11, 10));
	    m2.put(p33.getId(), new QuantiteCommande(p33, 5));
	    factureService.createFacture(cust7, m2, "Espèces");
	    HashMap<Long, QuantiteCommande> m21 = new HashMap<Long, QuantiteCommande>();
	    m21.put(p11.getId(), new QuantiteCommande(p11, 10));
	    m21.put(p33.getId(), new QuantiteCommande(p33, 5));;
	    HashMap<Long, QuantiteCommande> m22 = new HashMap<Long, QuantiteCommande>();
	    m22.put(p11.getId(), new QuantiteCommande(p11, 10));
	    m22.put(p33.getId(), new QuantiteCommande(p33, 5));
	    HashMap<Long, QuantiteCommande> m23 = new HashMap<Long, QuantiteCommande>();
	    m23.put(p11.getId(), new QuantiteCommande(p11, 10));
	    m23.put(p33.getId(), new QuantiteCommande(p33, 5));
	    factureService.createFacture(cust7, m21, "Espèces");
	    factureService.createFacture(cust7, m22, "Carte-bancaire");
	    factureService.createFacture(cust7, m23, "Chèque");
	    HashMap<Long, QuantiteCommande> m3 = new HashMap<Long, QuantiteCommande>();
	    m3.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m3.put(p21.getId(), new QuantiteCommande(p21, 1));
	    m3.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(cust6, m3, "Espèces");
	    HashMap<Long, QuantiteCommande> m4 = new HashMap<Long, QuantiteCommande>();
	    m4.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m4.put(p31.getId(), new QuantiteCommande(p31, 15));
	    m4.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(cust6, m4, "Chèque");
	    HashMap<Long, QuantiteCommande> m41 = new HashMap<Long, QuantiteCommande>();
	    m41.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m41.put(p31.getId(), new QuantiteCommande(p31, 15));
	    m41.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(cust6, m41, "Carte-bancaire");
	    HashMap<Long, QuantiteCommande> m42 = new HashMap<Long, QuantiteCommande>();
	    m42.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m42.put(p31.getId(), new QuantiteCommande(p31, 15));
	    m42.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(cust6, m42, "Chèque");
	    HashMap<Long, QuantiteCommande> m43 = new HashMap<Long, QuantiteCommande>();
	    m43.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m43.put(p31.getId(), new QuantiteCommande(p31, 15));
	    m43.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(cust6, m43, "Carte-bancaire");
	    HashMap<Long, QuantiteCommande> m44 = new HashMap<Long, QuantiteCommande>();
	    m44.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m44.put(p31.getId(), new QuantiteCommande(p31, 15));
	    m44.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(cust6, m44, "Espèces");
	    HashMap<Long, QuantiteCommande> m5 = new HashMap<Long, QuantiteCommande>();
	    m5.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m5.put(p31.getId(), new QuantiteCommande(p31, 1));
	    m5.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(null, m5, "Chèque");
	    HashMap<Long, QuantiteCommande> m6 = new HashMap<Long, QuantiteCommande>();
	    m6.put(p13.getId(), new QuantiteCommande(p13, 3));
	    m6.put(p31.getId(), new QuantiteCommande(p31, 5));
	    m6.put(p32.getId(), new QuantiteCommande(p32, 5));
	    factureService.createFacture(null, m6, "Chèque");
    }
}
