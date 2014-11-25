package m2.project.service.impl;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
//import static org.junit.Assert.fail;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import m2.project.Application;
import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
//import m2.project.service.CustomerGroupService;
import m2.project.service.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CustomerServiceImplTest {
	//@Autowired
    //private CustomerGroupService customerGroupService;
	@Autowired
	private CustomerService customerService;
	
	//private CustomerGroup g;
	//private List<CustomerGroup> cgl;

	private long c1ID, c2ID;
	private Customer c1, c2;

	@Before
	public void setUp() throws Exception {
    	////MockitoAnnotations.initMocks(this);
		
        customerService.deleteAll();
		//customerGroupService.deleteAll();
		
		// customer groups
		//g = new CustomerGroup("G1", 10);
		////when(customerGroupService.save(g)).thenReturn(g);
		
		//customerGroupService.save(g);
		
		//cgl = new ArrayList<CustomerGroup>();
		//cgl.add(g);
		
		// customers
		c1 = new Customer("FNC1", "LNC1", null);
		c2 = new Customer("FNC2", "LNC2", null);
		//c2 = new Customer("FNC2", "LNC2", cgl);
		customerService.save(c1);
		customerService.save(c2);
		c1ID = c1.getId();
		c2ID = c2.getId();
	}

	// test unoque constraint
	
	
	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findOne(long)}.
	 */
	@Test
	public void testFindOne() {
		Customer customer = customerService.findOne(c1ID);
		assertThat(customer, allOf(
                hasProperty("id", is(c1ID)),
                hasProperty("firstName", is("FNC1")),
                hasProperty("lastName", is("LNC1"))
                //hasProperty("customerGroups", is((List<CustomerGroup>)null))
        ));
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findAll(org.springframework.data.domain.Pageable)}.
	 */
	@Test
	public void testFindAllPageable() {
		Pageable pageable = new PageRequest(0, 10);
		Page<Customer> page = customerService.findAll(pageable);
		
		assertThat(page.getNumberOfElements(), is(2));
		Iterator<Customer> iterator = page.iterator();
		
		assertThat(iterator.next(), allOf(
				hasProperty("id", is(c1ID)),
				hasProperty("firstName", is("FNC1")),
                hasProperty("lastName", is("LNC1"))
                //hasProperty("customerGroups", is((List<CustomerGroup>)null))
        ));
		assertThat(iterator.next(), allOf(
				hasProperty("id", is(c2ID)),
				hasProperty("firstName", is("FNC2")),
                hasProperty("lastName", is("LNC2"))
                //hasProperty("customerGroups", is((List<CustomerGroup>)cgl))
        ));
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findAllOrderByLastNameAsc(org.springframework.data.domain.Pageable)}.
	 */
	@Test
	public void testFindAllOrderByLastNameAsc() {
		List<Customer> customers = customerService.findAll();
		assertThat(customers.size(), is(2));
		assertThat(customers.get(0), allOf(
                hasProperty("id", is(c1ID)),
                hasProperty("firstName", is("FNC1")),
                hasProperty("lastName", is("LNC1"))
                //hasProperty("customerGroups", is((List<CustomerGroup>)null))
        ));
		assertThat(customers.get(1), allOf(
                hasProperty("id", is(c2ID)),
                hasProperty("firstName", is("FNC2")),
                hasProperty("lastName", is("LNC2"))
                //hasProperty("customerGroups", is(cgl))
        ));
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#save(m2.project.model.Customer)}.
	 */
	@Test
	public void testSave() {
		Customer c3 = new Customer("FNC3", "LNC3", null);
		customerService.save(c3);
		//Customer c4 = new Customer("FNC4", "LNC4", cgl);
		//customerService.save(c4);
		long c3ID = c3.getId();
		//long c4ID = c4.getId();
		
		List<Customer> customers = customerService.findAll();
		assertThat(customers.size(), is(3));
		assertThat(customers.get(2), allOf(
                hasProperty("id", is(c3ID)),
                hasProperty("firstName", is("FNC3")),
                hasProperty("lastName", is("LNC3"))
                //hasProperty("customerGroups", is((List<CustomerGroup>)null))
        ));
		/*
		assertThat(customers.get(3), allOf(
                hasProperty("id", is(c4ID)),
                hasProperty("firstName", is("FNC4")),
                hasProperty("lastName", is("LNC4")),
                hasProperty("customerGroups", is(cgl))
        ));
         */
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#delete(long)}.
	 */
	@Test
	public void testDeleteLong() {
		customerService.delete(c1ID);
		List<Customer> customers = customerService.findAll();
		assertThat(customers.size(), is(1));
		assertThat(customers.get(0), allOf(
                hasProperty("id", is(c2ID)),
                hasProperty("firstName", is("FNC2")),
                hasProperty("lastName", is("LNC2"))
                //hasProperty("customerGroups", is(cgl))
        ));
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#delete(m2.project.model.Customer)}.
	 */
	@Test
	public void testDeleteCustomer() {
		customerService.delete(c1);
		List<Customer> customers = customerService.findAll();
		assertThat(customers.size(), is(1));
		assertThat(customers.get(0), allOf(
                hasProperty("id", is(c2ID)),
                hasProperty("firstName", is("FNC2")),
                hasProperty("lastName", is("LNC2"))
                //hasProperty("customerGroups", is(cgl))
        ));
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#deleteAll()}.
	 */
	@Test
	public void testDeleteAll() {
		customerService.deleteAll();
		List<Customer> customers = customerService.findAll();
		assertThat(customers.size(), is(0));
	}

	/**
	 * Test method for {@link m2.project.service.impl.CustomerServiceImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
		//fail("Not yet implemented");
		List<Customer> customers = customerService.findAll();
		assertThat(customers.size(), is(2));
		assertThat(customers.get(0), allOf(
				hasProperty("id", is(c1ID)),
				hasProperty("firstName", is("FNC1")),
                hasProperty("lastName", is("LNC1"))
                //hasProperty("customerGroups", is((List<CustomerGroup>)null))
        ));
		assertThat(customers.get(1), allOf(
				hasProperty("id", is(c2ID)),
				hasProperty("firstName", is("FNC2")),
                hasProperty("lastName", is("LNC2"))
                //hasProperty("customerGroups", is(cgl))
        ));
	}

}
