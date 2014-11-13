package m2.project.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import m2.project.Application;
import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.model.Employee;
import m2.project.model.Role;
import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;
import m2.project.service.EmployeeService;
import m2.project.service.RoleService;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.authentication.FormAuthConfig;


import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class) // specify which application context(s) that should be used in the test
//@WebAppConfiguration // tell Spring that a WebApplicationContext should be loaded for the test
//@IntegrationTest("server.port:0") // start the server on a random available port, useful when there are different services occupying different ports 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CustomerControllerTest {
	
	@InjectMocks
    CustomerController controller;

	@Mock
    CustomerService mockCustomerService;
    @Mock
    CustomerGroupService mockCustomerGroupService;
    
    @Mock
    View mockView;

    MockMvc mockMvc;

    /*
    @Before
    public void setUp() throws Exception {
    	
        //MockitoAnnotations.initMocks(this);
        //mockMvc = MockMvcBuilders.standaloneSetup(controller)
        		//.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        //        .setSingleView(mockView)
        //        .build();
       
    	
    	MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(new ViewResolver() {
                    @Override
                    public View resolveViewName(String viewName, Locale locale) throws Exception {
                        return new MappingJackson2JsonView();
                    }
                })
                .build();
        
        final Authentication authentication = new TestingAuthenticationToken("celine.gilet", "netapsys");
        final SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }*/
    
	Customer c1, c2, c3;
	//CustomerGroup g1, g2;
    
    @Before
    public void setup() throws Exception {
    	
    	MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        
        
        mockCustomerService.deleteAll();
		mockCustomerGroupService.deleteAll();
		
		// customer groups
		/*
		g1 = new CustomerGroup("G1", 10);
		g2 = new CustomerGroup("G2", 5);
		mockCustomerGroupService.save(g1);
		mockCustomerGroupService.save(g2);
		List<CustomerGroup> cgl1 = new ArrayList<CustomerGroup>();
		cgl1.add(g1);
		List<CustomerGroup> cgl2 = new ArrayList<CustomerGroup>();
		cgl2.add(g1);
		cgl2.add(g2);
		*/
		
		// customers
		c1 = new Customer("FNC1", "LNC1", null);
		//c2 = new Customer("FNC2", "LNC2", cgl1);
		//c3 = new Customer("FNC3", "LNC3", cgl2);
		mockCustomerService.save(c1);
		mockCustomerService.save(c2);
		mockCustomerService.save(c3);
    }
    
    
    @After
    public void tearDown() {
    	SecurityContextHolder.clearContext();
    }
    

    @Test
    public void testCustomersList() throws Exception {
        List<Customer> expectedCustomers = Arrays.asList(new Customer());
        when(mockCustomerService.findAll()).thenReturn(expectedCustomers);

        System.out.println("--------------------------------------------------------->" + expectedCustomers);
        
        //mockMvc.perform(get("/customer").principal(SecurityContextHolder.getContext().getAuthentication()))
        mockMvc.perform(
        	get("/customer")
        		.sessionAttr("custGroups", mockCustomerGroupService.findAll())
        		.sessionAttr("customer", new Customer()))
        	.andExpect(status().isOk())
            .andExpect(model().attribute("customers", expectedCustomers))
            .andExpect(view().name("/customer/customerslist"));
    }
    
    /*
    mockMvc.perform(post("/people")
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"firstName\":\"Tom\", \"lastName\":\"van Zummeren\"}".getBytes()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.identifier", equalTo("123")))
            .andExpect(jsonPath("$.allPeople[*].firstName", hasItem("Tom")));

    verify(mockPeopleService).persistPerson(new Person("Tom", "van Zummeren"));
    */
    
}	
	
	
	
	/*
	private MockMvc mockMvc;
	
	@Autowired
	RoleService roleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
    CustomerService customerService;
	@Autowired
	CustomerGroupService customerGroupService;
	
	//@Value("${local.server.port}")
    //int port;
	
	Role a, u;
	Employee e1, e2;
	Customer c1, c2, c3;
	CustomerGroup g1, g2;
	
	@Before
    public void setUp() throws Exception {
		
		// cette fonction setUp est appelée pour chaque test. Donc vider la base en début de setUp !!!
		employeeService.deleteAll();
		roleService.deleteAll();
		customerService.deleteAll();
		customerGroupService.deleteAll();
				
		// customer groups
		g1 = new CustomerGroup("G1", 10);
		g2 = new CustomerGroup("G2", 5);
		customerGroupService.save(g1);
		customerGroupService.save(g2);
		List<CustomerGroup> cgl1 = new ArrayList<CustomerGroup>();
		cgl1.add(g1);
		List<CustomerGroup> cgl2 = new ArrayList<CustomerGroup>();
		cgl2.add(g1);
		cgl2.add(g2);
		
		// customers
		c1 = new Customer("FNC1", "LNC1", null);
		c2 = new Customer("FNC2", "LNC2", cgl1);
		c3 = new Customer("FNC3", "LNC3", cgl2);
		customerService.save(c1);
		customerService.save(c2);
		customerService.save(c3);

		// roles
		Role a = new Role("ROLE_ADMIN", "Administrateur");
		Role u = new Role("ROLE_USER", "Utilisateur");
		roleService.save(a);
		roleService.save(u);
		
		// employees
		GregorianCalendar cal = new GregorianCalendar(1947, 02, 16);
		e1 = new Employee("EFN1", "ELN1", "", "", "", "", new Date(cal.getTimeInMillis()), "admin", "admin", a);
		e2 = new Employee("EFN2", "ELN2", "", "", "", "", new Date(cal.getTimeInMillis()), "user", "user", u);
		
		employeeService.save(e1);
		cal = new GregorianCalendar(1987, 01, 31);
		employeeService.save(e2);
		
		// instruct Rest Assured to use the correct port. It is an open source project that provides a Java DSL for testing restful services
        //RestAssured.port = port;
    }
	
	// test unique name
	// droits
    
	@Test
	public void formAuthenticationWithDefinedCsrfField() throws Exception {
		
        mockMvc.perform(get("/customer"))
        	.andExpect(status().isOk());
		
		/*
		RestAssured.
			given().
				//auth().form(e1.getLogin(), e1.getPassword(), FormAuthConfig.springSecurity()).
				auth().form(e1.getLogin(), e1.getPassword(), new FormAuthConfig("/process-login", "login", "pwd").withCsrfFieldName("_csrf")).
			expect().
				statusCode(HttpStatus.SC_OK).
				body(Matchers.equalTo("OK")).
			when().
				post("/process-login");
		*/
		
		/*
		// we're not authenticated, service returns "401 Unauthorized"
		RestAssured.
			expect().
			    statusCode(401).
			when().
			  get("/customer");
			 
		// with authentication it is working
		RestAssured.
			expect().
		    	statusCode(200).
		    when().
		    	with().
		    		authentication().basic(e1.getLogin(), e1.getPassword()).
		    			get("/customer");
		*/


	/*
	@Test
    public void testCustomersList() {

		RestAssured
    		.given()
    			.auth().form(e1.getLogin(), e1.getPassword(), new FormAuthConfig("/login", "login", "pwd"))
    		.when()
    			.get("/customer")
    			.then()
    				.statusCode(HttpStatus.SC_OK)
    				.body("lastName", Matchers.hasItems("FNC1", "FNC2", "FNC3"));
    }
	 */
	
	/*
    @Test
    public void testAjaxEditCustomerForm() {
    	RestAssured
    		.given()
    			.auth().form(e1.getLogin(), e1.getPassword(), new FormAuthConfig("/login", "login", "pwd"))
    			.parameters("id", c1.getId())
    		.when()
    			.get("/customer/edit")
    		.then()
                .statusCode(HttpStatus.SC_OK)
                .body("firstName", Matchers.is("FNC1"))
                .body("lastName", Matchers.is("LNC1"))
                .body("id", Matchers.is(c1.getId()));
    }

	@Test
	public void testAjaxSubmitCustomerForm() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDeleteCustomer() {
    	RestAssured
    		.given()
    			.auth().form(e1.getLogin(), e1.getPassword(), new FormAuthConfig("/login", "login", "pwd"))
	   		.when()
    			.delete("/customer/delete/{id}", c1.getId())
    		.then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
	}

	@Test
	public void testAjaxGroupPopover() {
		RestAssured
			.given()
				.auth().form(e1.getLogin(), e1.getPassword(), new FormAuthConfig("/login", "login", "pwd"))
				.parameters("id", c2.getId())
			.when()
				.get("/customer/grouppopover")
			.then()
	            .statusCode(HttpStatus.SC_OK)
	            .body("title", Matchers.is("G1"));
	}
     */