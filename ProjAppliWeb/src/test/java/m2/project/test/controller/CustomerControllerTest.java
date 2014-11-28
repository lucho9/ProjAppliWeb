package m2.project.test.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import m2.project.Application;
import m2.project.controller.CustomerController;
import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.authentication.FormAuthConfig;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CustomerControllerTest {
	private MockMvc mockMvc;
	 
    @Autowired
	private CustomerService customerService;

    @Autowired
    private CustomerGroupService customerGroupService;
    
	private long g1ID, g2ID;
	private CustomerGroup g1, g2;
	private long c1ID, c2ID;
	private Customer c1, c2;
	private List<CustomerGroup> lcg; 
	private List<Customer> lc; 
	
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController(customerService, customerGroupService))
        		.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        		.setValidator(new LocalValidatorFactoryBean())
        		.setViewResolvers(new ViewResolver() {
                    @Override
                    public View resolveViewName(String viewName, Locale locale) throws Exception {
                        return new MappingJackson2JsonView();
                    }
                })
                .build();
        
    	/*
        final Authentication authentication = new TestingAuthenticationToken("celine.gilet", "netapsys");
        final SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        */
        /*
        GrantedAuthority[] ga = new GrantedAuthority[] {new GrantedAuthorityImpl("ROLE_ADMIN")};
        TestingAuthenticationToken token = new TestingAuthenticationToken("admin", "password", ga);
        token.setAuthenticated(true);        
        SecurityContextHolder.getContext().setAuthentication(token);
        */
 
        // datas
        customerGroupService.deleteAll();
		g1 = new CustomerGroup("G1", 10.0);
		g2 = new CustomerGroup("G2", 5.0);
		customerGroupService.save(g1);
		customerGroupService.save(g2);
		g1ID = g1.getId();
		g2ID = g2.getId();
		lcg = new ArrayList<CustomerGroup>();
        lcg.add(g1);
        lcg.add(g2);
        
		customerService.deleteAll();
		c1 = new Customer("FNC1", "LNC1", "Mulhouse", "0033 6 00 00 00 01", "email@emails.com", null);
		c2 = new Customer("FNC2", "LNC2", "Mulhouse", "0033 6 00 00 00 01", "email@emails.com", lcg);
		customerService.save(c1);
		customerService.save(c2);
		c1ID = c1.getId();
		c2ID = c2.getId();
        lc = new ArrayList<Customer>();
        lc.add(c1);
        lc.add(c2);
    }
    
	//@After
    //public void tearDown() {
    //	SecurityContextHolder.clearContext();
    //}
    
    @Test
    public void testCustomersList() throws Exception {
        //mockMvc.perform(get("/customer").principal(SecurityContextHolder.getContext().getAuthentication()))
        
    	mockMvc.perform(
        	get("/customer")
        		.sessionAttr("custGroups", lcg)
        		.sessionAttr("customer", new Customer()))
        	.andExpect(status().isOk())
            .andExpect(view().name("/customer/customerslist"))
            .andExpect(model().attribute("customers", hasSize(2)))
            .andExpect(model().attribute("customers", hasItem(
            		allOf(
            				hasProperty("id", is(c1ID)),
            				hasProperty("lastName", is("LNC1")),
            				hasProperty("firstName", is("FNC1"))
            		)
            )))
            .andExpect(model().attribute("customers", hasItem(
            		allOf(
            				hasProperty("id", is(c2ID)),
            				hasProperty("lastName", is("LNC2")),
            				hasProperty("firstName", is("FNC2"))
            		)
            )));
    
    
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
	@Test
	public void formAuthenticationWithDefinedCsrfField() throws Exception {
		
        mockMvc.perform(get("/customer"))
        	.andExpect(status().isOk());
		
		
	}*/
}

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
