package m2.project.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import m2.project.customer.model.Customer;
import m2.project.customer.repository.CustomerRepository;

@Controller
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "/customer/create", method = RequestMethod.GET)
	public String createCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "/customer/create";
	}

	@RequestMapping(value = "/customer/create", method = RequestMethod.POST)
	public String submitCreateCustomerForm(@ModelAttribute Customer customer) {
		customerRepository.save(customer);
		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String customersList(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "customer/list";
	}

	@RequestMapping(value = "/customer/delete", method = RequestMethod.GET)
	public String deleteCustomer(@RequestParam("id") Long id) {
		customerRepository.delete(id);
		
		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {

		model.addAttribute("customer", customerRepository.findOne(id));
		return "/customer/create";
	}
	
	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
	public String submitEditForm(@ModelAttribute Customer customer) {

		customerRepository.save(customer);
		return "redirect:/customer";
	}
}
