package m2.project.controller;

import static org.junit.Assert.*;
import m2.project.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class CustomerControllerTest {

	@Test
	public void testCustomersList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjaxEditCustomerForm() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjaxSubmitCustomerForm() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjaxGroupPopover() {
		fail("Not yet implemented");
	}

}
