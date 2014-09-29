package m2.project.controller;

import javax.validation.Valid;

import m2.project.model.Customer;
import m2.project.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class CustomerController extends WebMvcConfigurerAdapter {
	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String customersList(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "/customer/list";
	}

	@RequestMapping(value = "/customer/create", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String createCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "/customer/create";
	}

	@RequestMapping(value = "/customer/create", method = RequestMethod.POST)
	public String submitCreateCustomerForm(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/customer/create";
		}

		customerRepository.save(customer);
		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public String editCustomerForm(@RequestParam("id") Long id, Model model) {

		model.addAttribute("customer", customerRepository.findOne(id));
		return "/customer/create";
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
	public String submitEditCustomerForm(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("customer", customer);
			return "/customer/create";
		}

		customerRepository.save(customer);
		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/delete", method = RequestMethod.GET)
	public String deleteCustomer(@RequestParam("id") Long id) {
		customerRepository.delete(id);
		return "redirect:/customer";
	}
}
