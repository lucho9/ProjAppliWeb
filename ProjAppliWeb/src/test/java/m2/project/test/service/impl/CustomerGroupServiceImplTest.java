package m2.project.test.service.impl;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import m2.project.Application;
import m2.project.model.CustomerGroup;
import m2.project.service.CustomerGroupService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CustomerGroupServiceImplTest {

	@Autowired
	private CustomerGroupService customerGroupService;
	
	private long g1ID, g2ID;
	private CustomerGroup g1, g2;
	
	@Before
	public void setUp() throws Exception {
		customerGroupService.deleteAll();
		g1 = new CustomerGroup("G1", 10.0);
		g2 = new CustomerGroup("G2", 5.0);
		customerGroupService.save(g1);
		customerGroupService.save(g2);
		
		g1ID = g1.getId();
		g2ID = g2.getId();
	}

	@Test
	public void testFindOne() {
		CustomerGroup customerGroup = customerGroupService.findOne(g1ID);
        assertThat(customerGroup, allOf(
                hasProperty("id", is(g1ID)),
                hasProperty("name", is("G1")),
                hasProperty("discount", is(10.0))
        ));
	}

	@Test
	public void testFindAll() {
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
        assertThat(customerGroups.size(), is(2));
        assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(g1ID)),
                hasProperty("name", is("G1")),
                hasProperty("discount", is(10.0))
        ));
        assertThat(customerGroups.get(1), allOf(
                hasProperty("id", is(g2ID)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5.0))
        ));
	}

	@Test
	public void testSave() {
		CustomerGroup g3 = new CustomerGroup("G3", 15.0);
		customerGroupService.save(g3);
		long g3ID = g3.getId();
		
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
		assertThat(customerGroups.size(), is(3));
        assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(g3ID)),
                hasProperty("name", is("G3")),
                hasProperty("discount", is(15.0))
        ));
		assertThat(customerGroups.get(1), allOf(
                hasProperty("id", is(g1ID)),
                hasProperty("name", is("G1")),
                hasProperty("discount", is(10.0))
        ));
        assertThat(customerGroups.get(2), allOf(
                hasProperty("id", is(g2ID)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5.0))
        ));
	}

	@Test
	public void testDeleteLong() {
		customerGroupService.delete(g1ID);
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
		assertThat(customerGroups.size(), is(1));
		assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(g2ID)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5.0))
        ));
	}

	@Test
	public void testDeleteCustomerGroup() {
		customerGroupService.delete(g1);
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
		assertThat(customerGroups.size(), is(1));
		assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(g2ID)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5.0))
        ));
	}

	@Test
	public void testDeleteAll() {
		customerGroupService.deleteAll();
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
		assertThat(customerGroups.size(), is(0));
	}

	@Test
	public void testGetGroupInfos() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"title\":\"G1\"");
		sb.append(", \"discount\":\"10.0\"");
		sb.append(", \"customers\":\"");
		sb.append("\"");
		sb.append("}");
		assertThat(customerGroupService.getGroupInfos(g1ID), equalTo(sb.toString()));
	}

}
