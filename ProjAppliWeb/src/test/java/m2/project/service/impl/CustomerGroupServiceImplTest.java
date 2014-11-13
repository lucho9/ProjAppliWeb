package m2.project.service.impl;

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
	
	private CustomerGroup g1, g2;
	
	@Before
	public void setUp() throws Exception {
		customerGroupService.deleteAll();

		g1 = new CustomerGroup("G1", 10);
		g2 = new CustomerGroup("G2", 5);
		customerGroupService.save(g1);
		customerGroupService.save(g2);
	}

	@Test
	public void testFindOne() {
		CustomerGroup customerGroup = customerGroupService.findOne(1L);
        assertThat(customerGroup, allOf(
                hasProperty("id", is(1L)),
                hasProperty("name", is("G1")),
                hasProperty("discount", is(10))
        ));
	}

	@Test
	public void testFindAll() {
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
        assertThat(customerGroups.size(), is(2));
        assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("name", is("G1")),
                hasProperty("discount", is(10))
        ));
        assertThat(customerGroups.get(1), allOf(
                hasProperty("id", is(2L)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5))
        ));
	}

	@Test
	public void testSave() {
		CustomerGroup g3 = new CustomerGroup("G3", 15);
		customerGroupService.save(g3);
		
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
		assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(1L)),
                hasProperty("name", is("G1")),
                hasProperty("discount", is(10))
        ));
        assertThat(customerGroups.get(1), allOf(
                hasProperty("id", is(2L)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5))
        ));
		assertThat(customerGroups.size(), is(3));
        assertThat(customerGroups.get(2), allOf(
                hasProperty("id", is(3L)),
                hasProperty("name", is("G3")),
                hasProperty("discount", is(15))
        ));
	}

	@Test
	public void testDeleteLong() {
		customerGroupService.delete(1L);
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
		assertThat(customerGroups.size(), is(1));
		assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(2L)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5))
        ));
	}

	@Test
	public void testDeleteCustomerGroup() {
		customerGroupService.delete(g1);
		List<CustomerGroup> customerGroups = customerGroupService.findAll();
		assertThat(customerGroups.size(), is(1));
		assertThat(customerGroups.get(0), allOf(
                hasProperty("id", is(2L)),
                hasProperty("name", is("G2")),
                hasProperty("discount", is(5))
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
		sb.append(", \"discount\":\"10\"");
		sb.append(", \"customers\":\"");
		sb.append("\"");
		sb.append("}");
		assertThat(customerGroupService.getGroupInfos(1L), equalTo(sb.toString()));
	}

}
