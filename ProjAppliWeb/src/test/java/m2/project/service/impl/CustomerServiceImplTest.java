package m2.project.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.mockito.stubbing.OngoingStubbing;

/**
 * @author Sment
 *
 */
public class CustomerServiceImplTest {
	@Mock
    CustomerGroupService mockCustomerGroupService;
	
	@InjectMocks
	@Autowired
    CustomerService customerService;
	
	private Customer c1, c2, c3;
	private CustomerGroup g1, g2;
	
	@Before
	public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        
        customerService.deleteAll();
		//mockCustomerGroupService.deleteAll();
		
		// customer groups
		g1 = new CustomerGroup("G1", 10);
		g2 = new CustomerGroup("G2", 5);
		//mockCustomerGroupService.save(g1);
		//mockCustomerGroupService.save(g2);
		
		// specify mock behave when method called
        when(mockCustomerGroupService.save(any(CustomerGroup.class))).thenReturn(g1);
        when(mockCustomerGroupService.save(any(CustomerGroup.class))).thenReturn(g1);
        
		List<CustomerGroup> cgl1 = new ArrayList<CustomerGroup>();
		cgl1.add(g1);
		List<CustomerGroup> cgl2 = new ArrayList<CustomerGroup>();
		cgl2.add(g1);
		cgl2.add(g2);
		
		//Mockito.when(mockCustomerGroupService.get(1L)).thenReturn(g1);
		//Mockito.when(mockCustomerGroupService.get(1L)).thenReturn(g1);
		
		// customers
		c1 = new Customer("FNC1", "LNC1", "", null);
		c2 = new Customer("FNC2", "LNC2", "", cgl1);
		c3 = new Customer("FNC3", "LNC3", "", cgl2);
		customerService.save(c1);
		customerService.save(c2);
		customerService.save(c3);
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findOne(long)}.
	 */
	@Test
	public void testFindOne() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findAll(org.springframework.data.domain.Pageable)}.
	 */
	@Test
	public void testFindAllPageable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findAllOrderByLastNameAsc(org.springframework.data.domain.Pageable)}.
	 */
	@Test
	public void testFindAllOrderByLastNameAsc() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#save(m2.project.model.Customer)}.
	 */
	@Test
	public void testSave() {
		
		
		//https://samerabdelkafi.wordpress.com/2013/07/01/junit-test-with-mockito-and-spring/
		//https://mockito.googlecode.com/hg-history/1.5/javadoc/org/mockito/Mockito.html
		
		  // specify mock behave when method called
        when(mockCustomerGroupService.save(any(CustomerGroup.class))).thenReturn(g1);
         
        //Assert.assertNotNull(invoiceService);
        //Map<Product, Integer> products = new HashMap<Product, Integer>();
        //products.put(new Product("labtop", BigDecimal.valueOf(1255.50)), 2);
        //Invoice invoice = invoiceService.processInvoice(products);
         
        //Assert.assertEquals(1255.50 * 2, invoice.getTotal().doubleValue(), 0);
         
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#delete(long)}.
	 */
	@Test
	public void testDeleteLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#delete(m2.project.model.Customer)}.
	 */
	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#deleteAll()}.
	 */
	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

}
