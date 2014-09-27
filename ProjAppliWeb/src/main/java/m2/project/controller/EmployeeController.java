package m2.project.controller;


import m2.project.model.Customer;
import m2.project.model.Employee;
import m2.project.repository.CustomerRepository;
import m2.project.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class EmployeeController {
	
	@Autowired
	private CustomerRepository customerRepository;
	private EmployeeRepository employeeRepository;
	
	
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String customersList(Model model) {
		model.addAttribute("employees", customerRepository.findAll());
		return "/employee/employees";
	}
	
	
	@RequestMapping(value = "/createemployee", method = RequestMethod.GET)
	public String createEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee/createEmployee";
	}
	
	@RequestMapping(value = "/createemployee", method = RequestMethod.POST)
	public String submitCreateEmployeeForm(@ModelAttribute Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/employees";
	}

	
	
	
	
}
